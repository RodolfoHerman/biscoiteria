package br.com.rodolfo.biscoiteria.domain.model.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum PedidoStatus {
    
    PENDENTE("Pendente"),
    ENTREGUE("Entregue", PENDENTE),
    CANCELADO("Cancelado", PENDENTE);

    private String descricao;
    private List<PedidoStatus> statusAnteriores;

    private PedidoStatus(String descricao, PedidoStatus... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public boolean naoPodeAlterarPara(PedidoStatus novoStatus) {
        if(novoStatus.equals(this)) {
            return Boolean.FALSE;
        }

        return !novoStatus.statusAnteriores.contains(this);
    }
}
