package br.com.rodolfo.biscoiteria.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import br.com.rodolfo.biscoiteria.api.model.ProdutoEncomendaModel;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface ProdutoEncomendaModelMapper {

    ProdutoEncomendaModel toModel(ProdutoEncomenda produtoEncomenda);

    List<ProdutoEncomendaModel> toCollection(List<ProdutoEncomenda> produtoEncomendas);
}
