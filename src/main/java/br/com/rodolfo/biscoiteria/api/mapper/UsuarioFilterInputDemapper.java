package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;

import br.com.rodolfo.biscoiteria.api.model.input.UsuarioFilterInput;
import br.com.rodolfo.biscoiteria.domain.filter.UsuarioFilter;

@Mapper(componentModel = "spring", imports = { })
public interface UsuarioFilterInputDemapper {

    UsuarioFilter toDomainObject(UsuarioFilterInput usuarioFilterInput);
}
