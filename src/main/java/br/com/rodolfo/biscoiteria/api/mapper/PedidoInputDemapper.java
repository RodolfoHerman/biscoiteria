package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.input.PedidoInput;
import br.com.rodolfo.biscoiteria.domain.model.Pedido;

@Mapper(componentModel = "spring", uses = { PedidoItemInputDemapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface PedidoInputDemapper {

    Pedido toDomainObject(PedidoInput pedidoInput);
}
