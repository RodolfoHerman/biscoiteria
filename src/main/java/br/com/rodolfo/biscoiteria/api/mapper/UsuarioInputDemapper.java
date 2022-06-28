package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.input.UsuarioInput;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface UsuarioInputDemapper {

    Usuario toDomainObject(UsuarioInput usuarioInput);

    void copyToDomainObject(UsuarioInput usuarioInput, @MappingTarget Usuario usuario);
}
