package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.mapper.function.ProdutoInputFunction;
import br.com.rodolfo.biscoiteria.api.model.input.ProdutoInput;
import br.com.rodolfo.biscoiteria.domain.model.Produto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { ProdutoInputFunction.class })
public interface ProdutoInputDemapper {

    Produto toDomainObject(ProdutoInput produtoInput);

    @Mapping(target = "categoria", expression = "java(ProdutoInputFunction.createProdutoCategoria(produtoInput))")
    void copyToDomainObject(ProdutoInput produtoInput, @MappingTarget Produto produto);
}
