package br.com.rodolfo.biscoiteria.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.rodolfo.biscoiteria.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("from Produto p left join fetch p.encomendas")
    Optional<Produto> findById(Long id);
}
