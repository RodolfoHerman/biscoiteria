package br.com.rodolfo.biscoiteria.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

public interface ProdutoEncomendaRepository extends JpaRepository<ProdutoEncomenda, Long> {

    List<ProdutoEncomenda> findByProduto_id(Long idProduto);
}
