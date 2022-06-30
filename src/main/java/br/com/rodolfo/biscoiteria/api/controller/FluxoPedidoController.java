package br.com.rodolfo.biscoiteria.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.biscoiteria.api.mapper.PedidoFormaPagamentoInputDemapper;
import br.com.rodolfo.biscoiteria.api.mapper.PedidoStatusInputDemapper;
import br.com.rodolfo.biscoiteria.api.model.input.PedidoFormaPagamentoInput;
import br.com.rodolfo.biscoiteria.api.model.input.PedidoStatusInput;
import br.com.rodolfo.biscoiteria.domain.model.Pedido;
import br.com.rodolfo.biscoiteria.domain.service.EmissaoPedidoService;
import br.com.rodolfo.biscoiteria.domain.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{pedido-id}")
public class FluxoPedidoController {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @Autowired
    private PedidoStatusInputDemapper pedidoStatusInputDemapper;

    @Autowired
    private PedidoFormaPagamentoInputDemapper pedidoFormaPagamentoInputDemapper;

    @PutMapping("/alteracao-status")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void alterarStatus(
        @PathVariable("pedido-id") Long pedidoId,
        @RequestBody @Valid PedidoStatusInput pedidoStatusInput
    ) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);

        pedidoStatusInputDemapper.copyToDomainObject(pedidoStatusInput, pedido);

        fluxoPedidoService.alterarStatus(pedido);
    }

    @PutMapping("/pagamento")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void pagamento(
        @PathVariable("pedido-id") Long pedidoId,
        @RequestBody @Valid PedidoFormaPagamentoInput pedidoFormaPagamentoInput
    ) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);

        pedidoFormaPagamentoInputDemapper.copyToDomainObject(pedidoFormaPagamentoInput, pedido);

        fluxoPedidoService.pagar(pedido);
    }
}
