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

import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoCategoriaRepository;
import br.com.rodolfo.biscoiteria.domain.service.CadastroProdutoCategoriaService;

@RestController
@RequestMapping("/categorias")
public class ProdutoCategoriaController {
 
    @Autowired
    private ProdutoCategoriaRepository produtoCategoriaRepository;

    @Autowired
    private CadastroProdutoCategoriaService cadastroProdutoCategoriaService;

    @GetMapping
    public List<ProdutoCategoria> listar() {
        return produtoCategoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProdutoCategoria buscar(@PathVariable("id") Long id) {
        return cadastroProdutoCategoriaService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProdutoCategoria salvar(@RequestBody ProdutoCategoria produtoCategoria) {
        return cadastroProdutoCategoriaService.salvar(produtoCategoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id") Long id) {
        cadastroProdutoCategoriaService.excluir(id);
    }

    @PutMapping("/{id}")
    public ProdutoCategoria atualizar(
        @PathVariable("id") Long id,
        @RequestBody ProdutoCategoria produtoCategoria
    ) {
        ProdutoCategoria produtoCategoriaSalvo = cadastroProdutoCategoriaService.buscarOuFalhar(id);

        BeanUtils.copyProperties(produtoCategoria, produtoCategoriaSalvo, "id");

        return cadastroProdutoCategoriaService.salvar(produtoCategoriaSalvo);
    }
}
