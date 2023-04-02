package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.model.Pedido;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.enums.PedidoStatus;
import br.com.rodolfo.biscoiteria.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void alterarStatus(Pedido pedido) {
        if(PedidoStatus.CANCELADO.equals(pedido.getStatus())) {

            pedido.cancelar();
            validarPedidoCancelamento(pedido);

        } else {
            pedido.entregar();
        }

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void pagar(Pedido pedido) {
        pedido.pagar();

        pedidoRepository.save(pedido);
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
