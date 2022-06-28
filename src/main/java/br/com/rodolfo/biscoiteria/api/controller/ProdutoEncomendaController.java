package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.biscoiteria.api.mapper.ProdutoEncomendaInputDemapper;
import br.com.rodolfo.biscoiteria.api.mapper.ProdutoEncomendaModelMapper;
import br.com.rodolfo.biscoiteria.api.model.ProdutoEncomendaModel;
import br.com.rodolfo.biscoiteria.api.model.input.ProdutoEncomendaInput;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoEncomendaRepository;
import br.com.rodolfo.biscoiteria.domain.service.CadastroProdutoEncomendaService;
import br.com.rodolfo.biscoiteria.domain.service.CadastroProdutoService;

@RestController
@RequestMapping("produtos/{produto-id}/encomendas")
public class ProdutoEncomendaController {

    @Autowired
    private ProdutoEncomendaRepository produtoEncomendaRepository;

    @Autowired
    private CadastroProdutoEncomendaService cadastroProdutoEncomendaService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private ProdutoEncomendaModelMapper produtoEncomendaModelMapper;

    @Autowired
    private ProdutoEncomendaInputDemapper produtoEncomendaInputDemapper;

    @GetMapping
    public List<ProdutoEncomendaModel> listar(@PathVariable("produto-id") Long produtoId) {
        Produto produto = cadastroProdutoService.buscarOuFalhar(produtoId);

        List<ProdutoEncomenda> produtoEncomendas = produtoEncomendaRepository.findByProduto(produto);

        return produtoEncomendaModelMapper.toCollection(produtoEncomendas);
    }

    @GetMapping("/{encomenda-id}")
    public ProdutoEncomendaModel buscar(
        @PathVariable("produto-id") Long produtoId,
        @PathVariable("encomenda-id") Long encomendaId
    ) {
        ProdutoEncomenda produtoEncomenda = cadastroProdutoEncomendaService.buscarOuFalhar(encomendaId, produtoId);

        return produtoEncomendaModelMapper.toModel(produtoEncomenda);
    }

    @PostMapping
    public ProdutoEncomendaModel salvar(
        @PathVariable("produto-id") Long produtoId,
        @RequestBody @Valid ProdutoEncomendaInput produtoEncomendaInput
    ) {
        Produto produto = cadastroProdutoService.buscarOuFalhar(produtoId);

        ProdutoEncomenda produtoEncomenda = produtoEncomendaInputDemapper.toDomainObject(produtoEncomendaInput);
        produtoEncomenda.setProduto(produto);

        return produtoEncomendaModelMapper.toModel(cadastroProdutoEncomendaService.salvar(produtoEncomenda));
    }

    @PutMapping("/{encomenda-id}")
    public ProdutoEncomendaModel atualizar(
        @PathVariable("produto-id") Long produtoId,
        @PathVariable("encomenda-id") Long encomendaId,
        @RequestBody @Valid ProdutoEncomendaInput produtoEncomendaInput
    ) {
        ProdutoEncomenda produtoEncomenda = cadastroProdutoEncomendaService.buscarOuFalhar(encomendaId, produtoId);

        produtoEncomendaInputDemapper.copyToDomainObject(produtoEncomendaInput, produtoEncomenda);

        return produtoEncomendaModelMapper.toModel(cadastroProdutoEncomendaService.salvar(produtoEncomenda));
    }
}
