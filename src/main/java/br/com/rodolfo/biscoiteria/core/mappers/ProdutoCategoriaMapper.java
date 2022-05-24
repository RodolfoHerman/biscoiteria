package br.com.rodolfo.biscoiteria.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.ProdutoCategoriaModel;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface ProdutoCategoriaMapper {

    ProdutoCategoriaModel toProdutoCategoriaModel(ProdutoCategoria produtoCategoria);
}
