package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;

import br.com.rodolfo.biscoiteria.api.model.input.PedidoFilterInput;
import br.com.rodolfo.biscoiteria.domain.filter.PedidoFilter;

@Mapper(componentModel = "spring", imports = { })
public interface PedidoFilterInputDemapper {

    PedidoFilter toDomainObject(PedidoFilterInput pedidoFilterInput);
}
