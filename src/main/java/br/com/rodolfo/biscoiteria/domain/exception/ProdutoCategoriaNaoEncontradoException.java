package br.com.rodolfo.biscoiteria.domain.exception;

public class ProdutoCategoriaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoCategoriaNaoEncontradoException(Long id) {
        super(String.format("Não existe um cadastro de categoria com o código '%d'", id));
    }
}
