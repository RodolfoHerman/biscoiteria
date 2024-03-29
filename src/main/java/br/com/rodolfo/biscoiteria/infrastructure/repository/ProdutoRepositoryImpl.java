package br.com.rodolfo.biscoiteria.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoFoto;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public ProdutoFoto save(ProdutoFoto foto) {
        return manager.merge(foto);
    }

    @Override
    @Transactional
    public void delete(ProdutoFoto foto) {
        manager.remove(foto);
    }
}
