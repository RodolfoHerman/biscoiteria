package br.com.rodolfo.biscoiteria.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.ProdutoModel;
import br.com.rodolfo.biscoiteria.core.mappers.functions.ProdutoFunction;
import br.com.rodolfo.biscoiteria.domain.model.Produto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { ProdutoFunction.class })
public interface ProdutoMapper {

    @Mapping(target = "encomenda", expression = "java(ProdutoFunction.getLastProdutoEncomenda(produto))")
    ProdutoModel toProdutoModel(Produto produto);
}
