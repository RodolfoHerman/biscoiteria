package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.model.Pedido;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.enums.PedidoStatus;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void alterarStatus(Pedido pedido) {
        if(PedidoStatus.CANCELADO.equals(pedido.getStatus())) {

            pedido.cancelar();
            validarPedidoCancelamento(pedido);

        } else {
            pedido.entregar();
        }
    }

    @Transactional
    public void pagar(Pedido pedido) {
        pedido.pagar();

        var mensagem = Mensagem.builder()
            .assunto("Pedido pago")
            .corpo("pedido-pago.html")
            .varialvel("pedido", pedido)
            .destinatario(pedido.getCliente().getEmail())
        .build();

        envioEmailService.enviar(mensagem);
    }

    private void validarPedidoCancelamento(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = cadastroProdutoService.buscarOuFalhar(item.getProduto().getId());
            produto.verificarAtualizarQuantidadeEstoque(-item.getQuantidade());

            item.setQuantidade(0);
            item.calcularValorTotal();
            item.calcularLucroTotal();
        });

        pedido.calcularValorTotal();
        pedido.calcularLucroTotal();
    }
}
