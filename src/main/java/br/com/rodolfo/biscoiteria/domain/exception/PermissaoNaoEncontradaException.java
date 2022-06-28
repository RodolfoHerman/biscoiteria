package br.com.rodolfo.biscoiteria.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public PermissaoNaoEncontradaException(Long id) {
        super(String.format("Não existe um cadastro de permissão com o código '%d'", id));
    }
}
