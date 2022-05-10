package br.com.rodolfo.biscoiteria.domain.exception;

public class ProdutoEncomendaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoEncomendaNaoEncontradoException(Long id) {
        super(String.format("Não existe um cadastro de encomenda com o código '%d'", id));
    }
}
