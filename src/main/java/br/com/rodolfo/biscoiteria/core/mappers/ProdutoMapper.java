package br.com.rodolfo.biscoiteria.core.mappers;

import java.util.Comparator;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.ProdutoEncomendaModel;
import br.com.rodolfo.biscoiteria.api.model.ProdutoModel;
import br.com.rodolfo.biscoiteria.domain.model.Produto;

@Mapper(componentModel = "spring", uses = { ProdutoEncomendaMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public abstract class ProdutoMapper {

    @AfterMapping
    protected void setLatestEncomenda(@MappingTarget ProdutoModel produtoModel) {
        ProdutoEncomendaModel encomenda = produtoModel.getEncomendas().stream()
            .sorted(Comparator.comparing(
                ProdutoEncomendaModel::getDataCadastro, Comparator.nullsLast(Comparator.reverseOrder())
            )).findFirst()
            .orElse(null);

        produtoModel.setEncomenda(encomenda);
    }

    public abstract ProdutoModel toProdutoModel(Produto produto);
}
