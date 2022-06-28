package br.com.rodolfo.biscoiteria.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.rodolfo.biscoiteria.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

    Optional<Usuario> findByTelefone(String telefone);
}
