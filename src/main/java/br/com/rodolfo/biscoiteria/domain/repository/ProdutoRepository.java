package br.com.rodolfo.biscoiteria.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rodolfo.biscoiteria.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    @Query("from Produto p inner join p.categoria where p.id = :id")
    Optional<Produto> findById(@Param("id") Long id);
}
