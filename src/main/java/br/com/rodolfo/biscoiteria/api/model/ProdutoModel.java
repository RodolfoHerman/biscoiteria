package br.com.rodolfo.biscoiteria.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    private Integer quantidadeEstoque;

    private LocalDate dataEncomenda;

    private BigDecimal precoVenda;

    private boolean ativo;

    private String categoria;
}
