package br.com.rodolfo.biscoiteria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodolfo.biscoiteria.domain.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> { }
