package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.rodolfo.biscoiteria.api.model.ProdutoEncomendaModel;
import br.com.rodolfo.biscoiteria.core.mappers.ProdutoEncomendaMapper;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import br.com.rodolfo.biscoiteria.domain.service.CadastroProdutoEncomenda;

@RestController
@RequestMapping("/encomendas")
public class ProdutoEncomendaController {

    @Autowired
    private CadastroProdutoEncomenda cadastroProdutoEncomenda;

    @Autowired
    private ProdutoEncomendaMapper produtoEncomendaMapper;

    @GetMapping("/por-id-produto/{id}")
    public List<ProdutoEncomendaModel> listarEncomendasPorIdProduto(@PathVariable("id") Long idProduto) {
        List<ProdutoEncomenda> encomendas = cadastroProdutoEncomenda.listarEncomendasPorIdProduto(idProduto);

        return encomendas.stream()
            .map(produtoEncomendaMapper::toProdutoEncomendaModel)
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProdutoEncomendaModel salvar(@RequestBody ProdutoEncomenda encomenda) {
        return produtoEncomendaMapper.toProdutoEncomendaModel(cadastroProdutoEncomenda.salvar(encomenda));
    }

    @PutMapping("/{id}")
    public ProdutoEncomendaModel atualizar(
        @PathVariable("id") Long id,
        @RequestBody ProdutoEncomenda encomenda
    ) {
        ProdutoEncomenda produtoEncomendaSalvo = cadastroProdutoEncomenda.buscarOuFalhar(id);

        BeanUtils.copyProperties(encomenda, produtoEncomendaSalvo, "id", "dataCadastro");

        return produtoEncomendaMapper.toProdutoEncomendaModel(cadastroProdutoEncomenda.salvar(produtoEncomendaSalvo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        cadastroProdutoEncomenda.excluir(id);
    }
}
