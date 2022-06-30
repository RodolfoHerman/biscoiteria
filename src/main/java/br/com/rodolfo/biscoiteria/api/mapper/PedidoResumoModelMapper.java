package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.PedidoResumoModel;
import br.com.rodolfo.biscoiteria.domain.model.Pedido;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface PedidoResumoModelMapper {

    PedidoResumoModel toModel(Pedido pedido);

    List<PedidoResumoModel> toCollection(Collection<Pedido> pedidos);
}
