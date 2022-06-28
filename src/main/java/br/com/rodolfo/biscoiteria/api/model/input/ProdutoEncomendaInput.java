package br.com.rodolfo.biscoiteria.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoEncomendaInput {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Positive
    private BigDecimal precoCompra;

    @NotNull
    @Positive
    private BigDecimal precoVenda;
}
