package br.com.rodolfo.biscoiteria.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import br.com.rodolfo.biscoiteria.domain.model.dto.EncomendaDTO;

@Mapper(componentModel = "spring")
public interface EncomendaMapper {

    @Mapping(target = "id", source = "encomenda.id")
    ProdutoEncomenda toProdutoEncomenda(EncomendaDTO encomenda, Produto produto);
}
