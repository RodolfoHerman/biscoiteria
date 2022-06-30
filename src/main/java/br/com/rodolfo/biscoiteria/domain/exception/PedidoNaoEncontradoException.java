package br.com.rodolfo.biscoiteria.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PedidoNaoEncontradoException(Long id) {
        super(String.format("Não existe um cadastro de pedido com o código '%d'", id));
    }
}
