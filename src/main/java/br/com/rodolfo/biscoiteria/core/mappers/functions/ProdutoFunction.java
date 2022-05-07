package br.com.rodolfo.biscoiteria.core.mappers.functions;

import java.util.Comparator;

import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

public interface ProdutoFunction {

    static ProdutoEncomenda getLastProdutoEncomenda(Produto produto) {
        return produto.getEncomendas().stream()
            .sorted(Comparator.comparing(
                ProdutoEncomenda::getDataCadastro, Comparator.nullsLast(Comparator.reverseOrder())
            ))
            .findFirst()
            .orElse(null);
    }
}
