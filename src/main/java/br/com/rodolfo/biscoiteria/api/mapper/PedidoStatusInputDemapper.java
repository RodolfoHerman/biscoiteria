package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.rodolfo.biscoiteria.api.model.input.PedidoStatusInput;
import br.com.rodolfo.biscoiteria.domain.model.Pedido;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface PedidoStatusInputDemapper {

    void copyToDomainObject(PedidoStatusInput pedidoStatusInput, @MappingTarget Pedido pedido);
}
