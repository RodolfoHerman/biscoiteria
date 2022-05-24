package br.com.rodolfo.biscoiteria.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.ProdutoEncomendaModel;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface ProdutoEncomendaMapper {

    ProdutoEncomendaModel toProdutoEncomendaModel(ProdutoEncomenda produtoEncomenda);
}
