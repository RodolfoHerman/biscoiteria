package br.com.rodolfo.biscoiteria.domain.exception;

public class GrupoEmUsoException extends EntidadeEmUsoException {

    public GrupoEmUsoException(Long id) {
        super(String.format("Grupo de código %d não pode ser removido, pois está em uso", id));
    }
}
