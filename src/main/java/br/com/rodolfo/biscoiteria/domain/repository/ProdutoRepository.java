package br.com.rodolfo.biscoiteria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodolfo.biscoiteria.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> { }
