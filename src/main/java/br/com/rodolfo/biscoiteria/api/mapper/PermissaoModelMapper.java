package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.PermissaoModel;
import br.com.rodolfo.biscoiteria.domain.model.Permissao;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION, imports = { })
public interface PermissaoModelMapper {

    PermissaoModel toModel(Permissao permissao);

    List<PermissaoModel> toCollection(Collection<Permissao> permissoes);
}
