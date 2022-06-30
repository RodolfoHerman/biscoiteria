package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.input.PedidoItemInput;
import br.com.rodolfo.biscoiteria.domain.model.PedidoItem;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface PedidoItemInputDemapper {

    @Mapping(target = "produto.id", source = "produtoId")
    PedidoItem toDomainObject(PedidoItemInput pedidoItemInput);
}
