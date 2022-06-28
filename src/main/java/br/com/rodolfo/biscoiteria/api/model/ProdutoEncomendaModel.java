package br.com.rodolfo.biscoiteria.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoEncomendaModel {

    private Long id;
    private Integer quantidade;
    private BigDecimal precoCompra;
    private BigDecimal precoTotal;
    private LocalDate dataCadastro;
}
