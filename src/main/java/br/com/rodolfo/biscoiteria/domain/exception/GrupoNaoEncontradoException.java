package br.com.rodolfo.biscoiteria.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public GrupoNaoEncontradoException(Long id) {
        super(String.format("Não existe um cadastro de grupo com o código '%d'", id));
    }
}
