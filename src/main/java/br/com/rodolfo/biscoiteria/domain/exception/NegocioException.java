package br.com.rodolfo.biscoiteria.domain.exception;

public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
