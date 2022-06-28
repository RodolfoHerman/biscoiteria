package br.com.rodolfo.biscoiteria.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    public UsuarioNaoEncontradoException(Long id) {
        super(String.format("Não existe um cadastro de usuário com o código '%d'", id));
    }
}
