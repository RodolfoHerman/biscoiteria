package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.UsuarioModel;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION, imports = { })
public interface UsuarioModelMapper {

    UsuarioModel toModel(Usuario usuario);

    List<UsuarioModel> toCollection(Collection<Usuario> usuarios);
}
