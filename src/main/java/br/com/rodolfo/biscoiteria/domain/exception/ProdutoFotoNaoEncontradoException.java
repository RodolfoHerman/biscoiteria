package br.com.rodolfo.biscoiteria.domain.exception;

public class ProdutoFotoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoFotoNaoEncontradoException(Long produtoId) {
        super(String.format("Não existe cadastro de foto para o produto de código %d", produtoId));
    }
}
