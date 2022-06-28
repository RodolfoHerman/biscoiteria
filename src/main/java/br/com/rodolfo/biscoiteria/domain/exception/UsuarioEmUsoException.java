package br.com.rodolfo.biscoiteria.domain.exception;

public class UsuarioEmUsoException extends EntidadeEmUsoException {

    public UsuarioEmUsoException(Long id) {
        super(String.format("Usuário de código %d não pode ser removido, pois está em uso", id));
    }
}
