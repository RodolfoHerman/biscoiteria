package br.com.rodolfo.biscoiteria.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.biscoiteria.domain.exception.ProdutoEncomendaNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoEncomendaRepository;

@Service
public class CadastroProdutoEncomenda {

    @Autowired
    private ProdutoEncomendaRepository produtoEncomendaRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    public ProdutoEncomenda salvar(ProdutoEncomenda produtoEncomenda) {
        Long idProduto = produtoEncomenda.getProduto().getId();

        Produto produto = cadastroProdutoService.buscarOuFalhar(idProduto);

        produtoEncomenda.setProduto(produto);
        
        return produtoEncomendaRepository.save(produtoEncomenda);
    }

    public void excluir(Long id) {
        try {
            produtoEncomendaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ProdutoEncomendaNaoEncontradoException(id);
        }
    }

    public List<ProdutoEncomenda> listarEncomendasPorIdProduto(Long idProduto) {
        return produtoEncomendaRepository.findByProduto_id(idProduto);
    }

    public ProdutoEncomenda buscarOuFalhar(Long id) {
        return produtoEncomendaRepository.findById(id)
            .orElseThrow(() -> new ProdutoEncomendaNaoEncontradoException(id));
    }
}
