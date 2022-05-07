package br.com.rodolfo.biscoiteria.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import br.com.rodolfo.biscoiteria.domain.model.dto.EncomendaDTO;

@Mapper(componentModel = "spring")
public interface ProdutoEncomendaMapper {

    @Mapping(target = "idProduto", source = "produtoEncomenda.produto.id")
    EncomendaDTO toEncomenda(ProdutoEncomenda produtoEncomenda);
}
