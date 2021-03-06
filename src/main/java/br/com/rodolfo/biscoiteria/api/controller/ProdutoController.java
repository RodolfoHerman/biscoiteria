package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.biscoiteria.api.mapper.ProdutoInputDemapper;
import br.com.rodolfo.biscoiteria.api.mapper.ProdutoModelMapper;
import br.com.rodolfo.biscoiteria.api.model.ProdutoModel;
import br.com.rodolfo.biscoiteria.api.model.input.ProdutoInput;
import br.com.rodolfo.biscoiteria.domain.exception.EntidadeNaoEncontradaException;
import br.com.rodolfo.biscoiteria.domain.exception.NegocioException;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepository;
import br.com.rodolfo.biscoiteria.domain.service.CadastroProdutoService;
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

    @GetMapping
    public Page<ProdutoModel> listar(
        @RequestParam(required = false) String nome,
        @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Produto> produtosPage = produtoRepository.findAll(ProdutoSpecs.comNomeSemelhante(nome), pageable);

        List<ProdutoModel> produtos = produtoModelMapper
            .toCollection(produtosPage.getContent());

        Page<ProdutoModel> produtosModelPage = new PageImpl<>(produtos, pageable,
            produtosPage.getTotalElements());

        return produtosModelPage;
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
}
