package br.com.rodolfo.biscoiteria.core.mappers.functions;

import java.util.Comparator;
import java.util.Objects;

import br.com.rodolfo.biscoiteria.api.model.ProdutoEncomendaModel;
import br.com.rodolfo.biscoiteria.api.model.ProdutoIdModel;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

public interface ProdutoFunction {

    static ProdutoEncomendaModel transformEncomendaToEncomendaModel(ProdutoEncomenda produtoEncomenda) {
        if(Objects.isNull(produtoEncomenda)) {
            return null;
        }
        
        ProdutoIdModel produtoIdModel = new ProdutoIdModel();
        produtoIdModel.setId(produtoEncomenda.getProduto().getId());

        return ProdutoEncomendaModel.builder()
            .id(produtoEncomenda.getId())
            .quantidade(produtoEncomenda.getQuantidade())
            .precoCompra(produtoEncomenda.getPrecoCompra())
            .dataCadastro(produtoEncomenda.getDataCadastro())
            .produto(produtoIdModel)
        .build();
    }

    static ProdutoEncomendaModel getLastProdutoEncomenda(Produto produto) {
        ProdutoEncomenda produtoEncomenda = produto.getEncomendas().stream()
            .sorted(Comparator.comparing(
                ProdutoEncomenda::getDataCadastro, Comparator.nullsLast(Comparator.reverseOrder())
            )).findFirst()
            .orElse(null);

        return transformEncomendaToEncomendaModel(produtoEncomenda);
    }
}
