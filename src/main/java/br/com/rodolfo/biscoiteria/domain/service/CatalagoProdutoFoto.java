package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoFoto;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepository;

@Service
public class CatalagoProdutoFoto {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoFoto salvar(ProdutoFoto foto) {
        return produtoRepository.save(foto);
    }
}
