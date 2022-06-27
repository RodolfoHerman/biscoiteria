package br.com.rodolfo.biscoiteria.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class ProdutoEncomendaModel {
    private Long id;

    private Integer quantidade;

    private BigDecimal precoCompra;

    private LocalDate dataCadastro;

    private ProdutoIdModel produto;
}
