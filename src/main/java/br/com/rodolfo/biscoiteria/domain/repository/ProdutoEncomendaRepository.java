package br.com.rodolfo.biscoiteria.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

public interface ProdutoEncomendaRepository extends JpaRepository<ProdutoEncomenda, Long> {

    @Query("from ProdutoEncomenda pe inner join fetch pe.produto where pe.produto.id = :idProduto")
    List<ProdutoEncomenda> findByProduto_id(@Param("idProduto") Long idProduto);
}
