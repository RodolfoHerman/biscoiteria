package br.com.rodolfo.biscoiteria.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemInput {

    @NotNull
    private Long produtoId;

    @NotNull
    @Positive
    private Integer quantidade;
}
