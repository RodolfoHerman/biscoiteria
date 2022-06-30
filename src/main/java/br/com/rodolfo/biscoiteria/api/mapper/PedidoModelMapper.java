package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.PedidoModel;
import br.com.rodolfo.biscoiteria.domain.model.Pedido;

@Mapper(componentModel = "spring", uses = { PedidoItemModelMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface PedidoModelMapper {

    PedidoModel toModel(Pedido pedido);

    List<PedidoModel> toCollection(Collection<Pedido> pedidos);
}
