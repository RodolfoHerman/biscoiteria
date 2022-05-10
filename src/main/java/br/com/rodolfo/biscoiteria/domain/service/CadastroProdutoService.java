package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.biscoiteria.core.mappers.ProdutoMapper;
import br.com.rodolfo.biscoiteria.domain.exception.ProdutoEmUsoException;
import br.com.rodolfo.biscoiteria.domain.exception.ProdutoNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;
import br.com.rodolfo.biscoiteria.domain.model.dto.ProdutoDTO;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoCategoriaService cadastroProdutoCategoriaService;

    @Autowired
    private ProdutoMapper produtoMapper;

    public Produto salvar(Produto produto) {
        Long idCategoria = produto.getCategoria().getId();

        ProdutoCategoria categoria = cadastroProdutoCategoriaService.buscarOuFalhar(idCategoria);

        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public void excluir(Long id) {
        try {
            produtoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new ProdutoEmUsoException(id);
        }
    }

    public ProdutoDTO buscarOuFalharDTO(Long id) {
        Produto produto = buscarOuFalhar(id);

        return produtoMapper.toProdutoDTO(produto);
    }

    public Produto buscarOuFalhar(Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }
}
