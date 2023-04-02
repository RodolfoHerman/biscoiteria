package br.com.rodolfo.biscoiteria.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.rodolfo.biscoiteria.domain.event.PedidoCanceladoEvent;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void aoCancelarPedido(PedidoCanceladoEvent pedidoCanceladoEvent) {
        var pedido = pedidoCanceladoEvent.getPedido();

        var mensagem = Mensagem.builder()
            .assunto("Pedido cancelado")
            .corpo("pedido-cancelado.html")
            .varialvel("pedido", pedido)
            .destinatario(pedido.getCliente().getEmail())
        .build();

        envioEmailService.enviar(mensagem);
    }
}
