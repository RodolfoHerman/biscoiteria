package br.com.rodolfo.biscoiteria.domain.model.enums;

import lombok.Getter;

@Getter
public enum PedidoStatus {
    
    PENDENTE("Pendente"),
    PAGO("Pago"),
    CANCELADO("Cancelado");

    private String descricao;

    private PedidoStatus(String descricao) {
        this.descricao = descricao;
    }
}
