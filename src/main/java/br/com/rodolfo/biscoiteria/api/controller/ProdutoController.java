package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;

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

import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.dto.ProdutoDTO;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepository;
import br.com.rodolfo.biscoiteria.domain.service.CadastroProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscar(@PathVariable("id") Long id) {
        return cadastroProdutoService.buscarOuFalharDTO(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Produto salvar(@RequestBody Produto produto) {
        return cadastroProdutoService.salvar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(
        @PathVariable("id") Long id,
        @RequestBody Produto produto
    ) {
        Produto produtoSalvo = cadastroProdutoService.buscarOuFalhar(id);

        BeanUtils.copyProperties(produto, produtoSalvo, "id");

        return cadastroProdutoService.salvar(produtoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        cadastroProdutoService.excluir(id);
    }
}
