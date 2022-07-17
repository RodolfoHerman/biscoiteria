package br.com.rodolfo.biscoiteria.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

public interface ProdutoEncomendaRepository extends JpaRepository<ProdutoEncomenda, Long> {

    Page<ProdutoEncomenda> findByProduto(Produto produto, Pageable pageable);

    Optional<ProdutoEncomenda> findByIdAndProduto_id(Long encomendaId, Long produtoId);
}
