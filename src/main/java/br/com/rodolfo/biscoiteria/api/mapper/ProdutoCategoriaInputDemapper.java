package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.input.ProdutoCategoriaInput;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION, imports = { })
public interface ProdutoCategoriaInputDemapper {

    ProdutoCategoria toDomainObject(ProdutoCategoriaInput produtoCategoriaInput);

    void copyToDomainObject(ProdutoCategoriaInput produtoCategoriaInput, @MappingTarget ProdutoCategoria produtoCategoria);
}
