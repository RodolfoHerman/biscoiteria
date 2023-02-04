package br.com.rodolfo.biscoiteria.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoFoto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto>, ProdutoRepositoryQueries {

    @Query("from Produto p inner join p.categoria where p.id = :id")
    Optional<Produto> findById(@Param("id") Long id);

    @Query("from ProdutoFoto f where f.produto.id = :id")
    Optional<ProdutoFoto> findFotoByProdutoId(@Param("id") Long id);
}
