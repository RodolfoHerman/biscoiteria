package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.ProdutoModel;
import br.com.rodolfo.biscoiteria.domain.model.Produto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface ProdutoModelMapper {

    @Mapping(target = "categoria", source = "produto.categoria.nome")
    ProdutoModel toModel(Produto produto);

    @Mapping(target = "categoria", source = "produto.categoria.nome")
    List<ProdutoModel> toCollection(List<Produto> produtos);
}
