package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.ProdutoFotoModel;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoFoto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface ProdutoFotoModelMapper {

    ProdutoFotoModel toModel(ProdutoFoto foto);
}
