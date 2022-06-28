package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.input.ProdutoEncomendaInput;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION, imports = { })
public interface ProdutoEncomendaInputDemapper {

    @Mapping(target = "atualizarEstoque", source = "atualizarEstoque", defaultValue = "true")
    ProdutoEncomenda toDomainObject(ProdutoEncomendaInput produtoEncomendaInput);

    void copyToDomainObject(ProdutoEncomendaInput produtoEncomendaInput, @MappingTarget ProdutoEncomenda produtoEncomenda);
}
