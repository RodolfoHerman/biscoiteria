package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.input.GrupoInput;
import br.com.rodolfo.biscoiteria.domain.model.Grupo;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface GrupoInputDemapper {

    Grupo toDomainObject(GrupoInput grupoInput);

    void copyToDomainObject(GrupoInput grupoInput, @MappingTarget Grupo grupo);
}
