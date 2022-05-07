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
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import br.com.rodolfo.biscoiteria.domain.model.dto.EncomendaDTO;
import br.com.rodolfo.biscoiteria.domain.service.CadastroProdutoEncomenda;

@RestController
@RequestMapping("/encomendas")
public class ProdutoEncomendaController {

    @Autowired
    private CadastroProdutoEncomenda cadastroProdutoEncomenda;

    @GetMapping("/por-id-produto/{id}")
    public List<EncomendaDTO> listarByIdProduto(@PathVariable("id") Long idProduto) {
        return cadastroProdutoEncomenda.listarEncomendasPorIdProduto(idProduto);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProdutoEncomenda salvar(@RequestBody EncomendaDTO encomenda) {
        return cadastroProdutoEncomenda.salvar(encomenda);
    }

    @PutMapping("/{id}")
    public ProdutoEncomenda atualizar(
        @PathVariable("id") Long id,
        @RequestBody EncomendaDTO encomenda
    ) {
        ProdutoEncomenda produtoEncomendaSalvo = cadastroProdutoEncomenda.buscarOuFalhar(id);

        BeanUtils.copyProperties(encomenda, produtoEncomendaSalvo, "id", "dataCadastro");

        Produto produto = new Produto();
        produto.setId(encomenda.getIdProduto());

        produtoEncomendaSalvo.setProduto(produto);

        return cadastroProdutoEncomenda.salvar(produtoEncomendaSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        cadastroProdutoEncomenda.excluir(id);
    }
}
