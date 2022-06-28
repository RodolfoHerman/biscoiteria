package br.com.rodolfo.biscoiteria.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

public interface ProdutoEncomendaRepository extends JpaRepository<ProdutoEncomenda, Long> {

    List<ProdutoEncomenda> findByProduto(Produto produto);

    Optional<ProdutoEncomenda> findByIdAndProduto_id(Long encomendaId, Long produtoId);
}
