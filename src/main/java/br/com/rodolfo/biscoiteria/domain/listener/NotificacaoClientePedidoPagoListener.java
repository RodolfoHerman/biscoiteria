package br.com.rodolfo.biscoiteria.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.rodolfo.biscoiteria.domain.event.PedidoPagoEvent;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService.Mensagem;;

@Component
public class NotificacaoClientePedidoPagoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void aoPagarPedido(PedidoPagoEvent pedidoPagoEvent) {
        var pedido = pedidoPagoEvent.getPedido();

        var mensagem = Mensagem.builder()
            .assunto("Pedido pago")
            .corpo("pedido-pago.html")
            .varialvel("pedido", pedido)
            .destinatario(pedido.getCliente().getEmail())
        .build();

        envioEmailService.enviar(mensagem);
    }
}
