package br.com.rodolfo.biscoiteria.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ProdutoModel {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoVenda;

    private boolean ativo;

    private ProdutoCategoriaModel categoria;

    @JsonIgnoreProperties("produto")
    private ProdutoEncomendaModel encomenda;

    @JsonIgnore
    private List<ProdutoEncomendaModel> encomendas = new ArrayList<>();
}
