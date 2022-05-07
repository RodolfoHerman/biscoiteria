package br.com.rodolfo.biscoiteria.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.biscoiteria.core.mappers.ProdutoMapper;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;
import br.com.rodolfo.biscoiteria.domain.model.dto.ProdutoDTO;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

    private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto de código '%s' não encontrado.";

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
            throw new EmptyResultDataAccessException(String.format(MSG_PRODUTO_NAO_ENCONTRADO, 1), 1);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                String.format("Produto de código %d não pode ser removido, pois está em uso", id));
        }
    }

    public ProdutoDTO buscarOuFalharDTO(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_PRODUTO_NAO_ENCONTRADO, id)));

        return produtoMapper.toProdutoDTO(produto);
    }

    public Produto buscarOuFalhar(Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_PRODUTO_NAO_ENCONTRADO, id)));
    }
}
