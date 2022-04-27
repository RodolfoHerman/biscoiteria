package br.com.rodolfo.biscoiteria.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoCategoriaRepository;

@Service
public class CadastroProdutoCategoriaService {

    private static final String MSG_CATEGORIA_NAO_ENCONTRADA = "Categoria de código '%s' não encontrada.";

    @Autowired
    private ProdutoCategoriaRepository produtoCategoriaRepository;

    public ProdutoCategoria salvar(ProdutoCategoria produtoCategoria) {
        return produtoCategoriaRepository.save(produtoCategoria);
    }

    public void excluir(Long id) {
        try {
            produtoCategoriaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(MSG_CATEGORIA_NAO_ENCONTRADA, 1);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                String.format("Categoria de código %d não pode ser removida, pois está em uso", id));
        }
    }

    public ProdutoCategoria buscarOuFalhar(Long id) {
        return produtoCategoriaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_CATEGORIA_NAO_ENCONTRADA, id)));
    }
}
