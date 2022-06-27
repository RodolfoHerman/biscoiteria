package br.com.rodolfo.biscoiteria.domain.exception;

public class ProdutoCategoriaEmUsoException extends EntidadeEmUsoException {

    public ProdutoCategoriaEmUsoException(Long id) {
        super(String.format("Categoria de código %d não pode ser removida, pois está em uso", id));
    }

    public ProdutoCategoriaEmUsoException(String nome) {
        super(String.format("Categoria de nome %s não pode ser salva, pois já está em uso", nome));
    }
}
