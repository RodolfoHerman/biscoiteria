package br.com.rodolfo.biscoiteria.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoNaoEncontradoException(Long id) {
        super(String.format("Não existe um cadastro de produto com o código '%d'", id));
    }    
}
