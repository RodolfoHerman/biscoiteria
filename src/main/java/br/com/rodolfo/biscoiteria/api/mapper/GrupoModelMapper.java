package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.GrupoModel;
import br.com.rodolfo.biscoiteria.domain.model.Grupo;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface GrupoModelMapper {

    GrupoModel toModel(Grupo grupo);

    List<GrupoModel> toCollection(Collection<Grupo> grupos);
}
