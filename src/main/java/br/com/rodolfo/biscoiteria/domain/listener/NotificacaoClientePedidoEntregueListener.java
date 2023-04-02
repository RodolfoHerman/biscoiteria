package br.com.rodolfo.biscoiteria.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.rodolfo.biscoiteria.domain.event.PedidoEntregueEvent;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoEntregueListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void aoEntregarPedido(PedidoEntregueEvent pedidoEntregueEvent) {
        var pedido = pedidoEntregueEvent.getPedido();

        var mensagem = Mensagem.builder()
            .assunto("Pedido entregue")
            .corpo("pedido-entregue.html")
            .varialvel("pedido", pedido)
            .destinatario(pedido.getCliente().getEmail())
        .build();

        envioEmailService.enviar(mensagem);
    }
}
