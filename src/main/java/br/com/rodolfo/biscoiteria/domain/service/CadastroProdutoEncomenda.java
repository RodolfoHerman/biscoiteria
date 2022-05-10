package br.com.rodolfo.biscoiteria.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.biscoiteria.core.mappers.EncomendaMapper;
import br.com.rodolfo.biscoiteria.core.mappers.ProdutoEncomendaMapper;
import br.com.rodolfo.biscoiteria.domain.exception.ProdutoEncomendaNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import br.com.rodolfo.biscoiteria.domain.model.dto.EncomendaDTO;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoEncomendaRepository;

@Service
public class CadastroProdutoEncomenda {

    @Autowired
    private ProdutoEncomendaRepository produtoEncomendaRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private ProdutoEncomendaMapper produtoEncomendaMapper;

    @Autowired
    private EncomendaMapper encomendaMapper;

    public ProdutoEncomenda salvar(ProdutoEncomenda produtoEncomenda) {
        return salvar(produtoEncomendaMapper.toEncomenda(produtoEncomenda));
    }

    public ProdutoEncomenda salvar(EncomendaDTO encomenda) {
        Produto produto = cadastroProdutoService.buscarOuFalhar(encomenda.getIdProduto());

        ProdutoEncomenda produtoEncomenda = encomendaMapper.toProdutoEncomenda(encomenda, produto);

        return produtoEncomendaRepository.save(produtoEncomenda);
    }

    public void excluir(Long id) {
        try {
            produtoEncomendaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ProdutoEncomendaNaoEncontradoException(id);
        }
    }

    public List<EncomendaDTO> listarEncomendasPorIdProduto(Long idProduto) {
        return produtoEncomendaRepository.findByProduto_id(idProduto)
            .stream().map(produtoEncomendaMapper::toEncomenda)
            .collect(Collectors.toList());
    }

    public ProdutoEncomenda buscarOuFalhar(Long id) {
        return produtoEncomendaRepository.findById(id)
            .orElseThrow(() -> new ProdutoEncomendaNaoEncontradoException(id));
    }
}
