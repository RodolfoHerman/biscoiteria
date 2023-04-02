package br.com.rodolfo.biscoiteria.domain.event;

import br.com.rodolfo.biscoiteria.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoEntregueEvent {

    private Pedido pedido;
}
