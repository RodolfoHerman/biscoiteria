package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.exception.ProdutoEmUsoException;
import br.com.rodolfo.biscoiteria.domain.exception.ProdutoNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoCategoriaService cadastroProdutoCategoriaService;

    @Transactional
    public Produto salvar(Produto produto) {
        Long idCategoria = produto.getCategoria().getId();

        ProdutoCategoria categoria = cadastroProdutoCategoriaService.buscarOuFalhar(idCategoria);

        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            produtoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new ProdutoEmUsoException(id);
        }
    }

    public Produto buscarOuFalhar(Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }
}
