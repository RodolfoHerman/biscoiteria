package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.biscoiteria.domain.exception.ProdutoCategoriaEmUsoException;
import br.com.rodolfo.biscoiteria.domain.exception.ProdutoCategoriaNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoCategoriaRepository;

@Service
public class CadastroProdutoCategoriaService {

    @Autowired
    private ProdutoCategoriaRepository produtoCategoriaRepository;

    public ProdutoCategoria salvar(ProdutoCategoria produtoCategoria) {
        return produtoCategoriaRepository.save(produtoCategoria);
    }

    public void excluir(Long id) {
        try {
            produtoCategoriaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoCategoriaNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new ProdutoCategoriaEmUsoException(id);
        }
    }

    public ProdutoCategoria buscarOuFalhar(Long id) {
        return produtoCategoriaRepository.findById(id)
            .orElseThrow(() -> new ProdutoCategoriaNaoEncontradoException(id));
    }
}
