package br.com.rodolfo.biscoiteria.domain.exception;

public class ProdutoEncomendaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoEncomendaNaoEncontradoException(Long encomendaId, Long produtoId) {
        super(String.format("Não existe cadastro de encomenda com código %d para o produto de código %d", encomendaId, produtoId));
    }
}
