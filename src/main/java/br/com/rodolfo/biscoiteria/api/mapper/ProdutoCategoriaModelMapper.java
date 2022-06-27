package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.ProdutoCategoriaModel;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION, imports = { })
public interface ProdutoCategoriaModelMapper {

    ProdutoCategoriaModel toModel(ProdutoCategoria produtoCategoria);

    List<ProdutoCategoriaModel> toCollection(List<ProdutoCategoria> produtoCategorias);
}
