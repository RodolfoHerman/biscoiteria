package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.UsuarioResumoModel;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION, imports = { })
public interface UsuarioResumoModelMapper {

    UsuarioResumoModel toModel(Usuario usuario);

    List<UsuarioResumoModel> toCollection(Collection<Usuario> usuarios);
}
