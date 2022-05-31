package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import br.com.rodolfo.biscoiteria.api.model.ProdutoModel;
import br.com.rodolfo.biscoiteria.core.mappers.ProdutoMapper;
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
    private ProdutoMapper produtoMapper;

    @GetMapping
    public List<ProdutoModel> listar() {
        return produtoRepository.findAll().stream()
            .map(produtoMapper::toProdutoModel)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProdutoModel buscar(@PathVariable("id") Long id) {
        return produtoMapper.toProdutoModel(cadastroProdutoService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProdutoModel salvar(@RequestBody @Valid Produto produto) {
        return produtoMapper.toProdutoModel(cadastroProdutoService.salvar(produto));
    }

    @PutMapping("/{id}")
    public ProdutoModel atualizar(
        @PathVariable("id") Long id,
        @RequestBody @Valid Produto produto
    ) {
        Produto produtoSalvo = cadastroProdutoService.buscarOuFalhar(id);

        BeanUtils.copyProperties(produto, produtoSalvo, "id");

        return produtoMapper.toProdutoModel(cadastroProdutoService.salvar(produtoSalvo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        cadastroProdutoService.excluir(id);
    }
}
