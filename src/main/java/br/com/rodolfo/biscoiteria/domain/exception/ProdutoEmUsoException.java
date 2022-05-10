package br.com.rodolfo.biscoiteria.domain.exception;

public class ProdutoEmUsoException extends EntidadeEmUsoException {

    public ProdutoEmUsoException(Long id) {
        super(String.format("Produto de código %d não pode ser removido, pois está em uso", id));
    }
}
