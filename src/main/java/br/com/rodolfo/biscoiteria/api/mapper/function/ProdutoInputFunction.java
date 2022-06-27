package br.com.rodolfo.biscoiteria.api.mapper.function;

import br.com.rodolfo.biscoiteria.api.model.input.ProdutoInput;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;

public interface ProdutoInputFunction {

    static ProdutoCategoria createProdutoCategoria(ProdutoInput produtoInput) {
        ProdutoCategoria produtoCategoria = new ProdutoCategoria();
        produtoCategoria.setId(produtoInput.getCategoria().getId());

        return produtoCategoria;
    }
}
