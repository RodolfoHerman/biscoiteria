package br.com.rodolfo.biscoiteria.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodolfo.biscoiteria.api.mapper.ProdutoFotoInputDemapper;
import br.com.rodolfo.biscoiteria.api.mapper.ProdutoFotoModelMapper;
import br.com.rodolfo.biscoiteria.api.mapper.ProdutoInputDemapper;
import br.com.rodolfo.biscoiteria.api.mapper.ProdutoModelMapper;
import br.com.rodolfo.biscoiteria.api.model.ProdutoFotoModel;
import br.com.rodolfo.biscoiteria.api.model.ProdutoModel;
import br.com.rodolfo.biscoiteria.api.model.input.ProdutoFotoInput;
import br.com.rodolfo.biscoiteria.api.model.input.ProdutoInput;
import br.com.rodolfo.biscoiteria.domain.exception.EntidadeNaoEncontradaException;
import br.com.rodolfo.biscoiteria.domain.exception.NegocioException;
import br.com.rodolfo.biscoiteria.domain.exception.ProdutoFotoNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepository;
import br.com.rodolfo.biscoiteria.domain.service.CadastroProdutoService;
import br.com.rodolfo.biscoiteria.domain.service.CatalagoProdutoFoto;
import br.com.rodolfo.biscoiteria.domain.service.FotoStorageService;
import br.com.rodolfo.biscoiteria.infrastructure.repository.spec.ProdutoSpecs;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private ProdutoModelMapper produtoModelMapper;

    @Autowired
    private ProdutoInputDemapper produtoInputDemapper;

    @Autowired
    private CatalagoProdutoFoto catalagoProdutoFoto;

    @Autowired
    private ProdutoFotoModelMapper produtoFotoModelMapper;

    @Autowired
    private ProdutoFotoInputDemapper produtoFotoInputDemapper;

    @Autowired
    private FotoStorageService fotoStorageService;

    @GetMapping
    public Page<ProdutoModel> listar(
        @RequestParam(required = false) String nome,
        @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Produto> produtosPage = produtoRepository.findAll(ProdutoSpecs.comNomeSemelhante(nome), pageable);

        List<ProdutoModel> produtos = produtoModelMapper
            .toCollection(produtosPage.getContent());

        return new PageImpl<>(produtos, pageable,
            produtosPage.getTotalElements());
    }

    @GetMapping("/{id}")
    public ProdutoModel buscar(@PathVariable("id") Long id) {
        return produtoModelMapper.toModel(cadastroProdutoService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProdutoModel salvar(@RequestBody @Valid ProdutoInput produtoInput) {
        Produto produto = produtoInputDemapper.toDomainObject(produtoInput);

        return produtoModelMapper.toModel(cadastroProdutoService.salvar(produto));
    }

    @PutMapping("/{id}")
    public ProdutoModel atualizar(
        @PathVariable("id") Long id,
        @RequestBody @Valid ProdutoInput produtoInput
    ) {
        Produto produto = cadastroProdutoService.buscarOuFalhar(id);

        produtoInputDemapper.copyToDomainObject(produtoInput, produto);

        try {
            return produtoModelMapper.toModel(cadastroProdutoService.salvar(produto));
        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @PutMapping("/{id}/atualizar-estoque")
    public ProdutoModel atualizarEncomenda(
        @PathVariable("id") Long id,
        @RequestBody Integer quantidadeEstoque
    ) {
        return produtoModelMapper.toModel(
            cadastroProdutoService.atualizarEstoque(id, quantidadeEstoque));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        cadastroProdutoService.excluir(id);
    }

    @PutMapping(path = "/{id}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProdutoFotoModel atualizarFoto(
        @PathVariable("id") Long id,
        @Valid ProdutoFotoInput produtoFotoInput
    ) throws IOException {
        Produto produto = cadastroProdutoService.buscarOuFalhar(id);

        MultipartFile multipartFile = produtoFotoInput.getArquivo();

        var foto = produtoFotoInputDemapper.toDomainObject(
            produto, multipartFile);

        return produtoFotoModelMapper.toModel(
            catalagoProdutoFoto.salvar(foto, multipartFile.getInputStream()));
    }

    @GetMapping(path = "/{id}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoFotoModel buscarProdutoFoto(@PathVariable("id") Long id) {
        return produtoFotoModelMapper.toModel(
            catalagoProdutoFoto.buscarOuFalhar(id));
    }

    @GetMapping("/{id}/foto")
    public ResponseEntity<?> servirFoto(
        @PathVariable("id") Long id,
        @RequestHeader(name = "accept") String acceptHeader
    ) throws HttpMediaTypeNotAcceptableException {
        try {
            var foto = catalagoProdutoFoto.buscarOuFalhar(id);
            var mediaTypeFoto = MediaType.parseMediaType(foto.getContentType());
            var mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

            var fotoRecuperada = fotoStorageService.recuperar(foto.getNomeArquivo());

            if(fotoRecuperada.temUrl()) {
                return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                .build();

            } else {

                return ResponseEntity.ok()
                    .contentType(mediaTypeFoto)
                    .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }

        } catch (ProdutoFotoNaoEncontradoException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/foto")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removerFoto(@PathVariable("id") Long id) {
        catalagoProdutoFoto.excluir(id);
    }

    private void verificarCompatibilidadeMediaType(
        MediaType mediaTypeFoto,
        List<MediaType> mediaTypesAceitas
    ) throws HttpMediaTypeNotAcceptableException {
        var aceita = mediaTypesAceitas.stream()
            .anyMatch(value -> value.isCompatibleWith(mediaTypeFoto));

        if(!aceita) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }
}
