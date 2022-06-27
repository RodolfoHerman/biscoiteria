package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<ProdutoModel> listar() {
        return produtoModelMapper.toCollection(produtoRepository.findAll());
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

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        cadastroProdutoService.excluir(id);
    }
}
