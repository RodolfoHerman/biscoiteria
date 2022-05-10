package br.com.rodolfo.biscoiteria.domain.exception;

public abstract class EntidadeEmUsoException extends NegocioException {

    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
