package br.com.rodolfo.biscoiteria.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFormaPagamentoInput {

    @NotBlank
    private String formaPagamento;

    private String observacao;
}
