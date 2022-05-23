package br.com.rodolfo.biscoiteria.core.mappers;

import org.mapstruct.Mapper;

import br.com.rodolfo.biscoiteria.api.model.ProdutoEncomendaModel;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

@Mapper(componentModel = "spring")
public interface ProdutoEncomendaMapper {

    ProdutoEncomendaModel toProdutoEncomendaModel(ProdutoEncomenda produtoEncomenda);
}
