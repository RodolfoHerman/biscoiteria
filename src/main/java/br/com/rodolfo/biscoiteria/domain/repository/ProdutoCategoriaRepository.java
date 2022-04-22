package br.com.rodolfo.biscoiteria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;

public interface ProdutoCategoriaRepository extends JpaRepository<ProdutoCategoria, Long> { }
