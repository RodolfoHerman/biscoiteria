package br.com.rodolfo.biscoiteria.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        produtoCategoriaRepository.deleteById(id);
    }

    public ProdutoCategoria buscarOuFalhar(Long id) {
        return produtoCategoriaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Categoria de código '%s' não encontrada.", id)));
    }
}
