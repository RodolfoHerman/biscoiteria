package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.PedidoItemModel;
import br.com.rodolfo.biscoiteria.domain.model.PedidoItem;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface PedidoItemModelMapper {

    @Mapping(target = "produtoId", source = "produto.id")
    @Mapping(target = "produtoNome", source = "produto.nome")
    PedidoItemModel toModel(PedidoItem pedidoItem);
}
