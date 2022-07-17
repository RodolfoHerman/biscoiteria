package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.biscoiteria.api.mapper.PedidoInputDemapper;
import br.com.rodolfo.biscoiteria.api.mapper.PedidoModelMapper;
import br.com.rodolfo.biscoiteria.api.mapper.PedidoResumoModelMapper;
import br.com.rodolfo.biscoiteria.api.model.PedidoModel;
import br.com.rodolfo.biscoiteria.api.model.PedidoResumoModel;
import br.com.rodolfo.biscoiteria.api.model.input.PedidoInput;
import br.com.rodolfo.biscoiteria.domain.exception.EntidadeNaoEncontradaException;
import br.com.rodolfo.biscoiteria.domain.exception.NegocioException;
import br.com.rodolfo.biscoiteria.domain.model.Pedido;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;
import br.com.rodolfo.biscoiteria.domain.repository.PedidoRepository;
import br.com.rodolfo.biscoiteria.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoModelMapper pedidoModelMapper;

    @Autowired
    private PedidoInputDemapper pedidoInputDemapper;

    @Autowired
    private PedidoResumoModelMapper pedidoResumoModelMapper;

    @GetMapping
    public Page<PedidoResumoModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Pedido> pedidosPage = pedidoRepository.findAll(pageable);

        List<PedidoResumoModel> pedidos = pedidoResumoModelMapper
            .toCollection(pedidosPage.getContent());

        Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(pedidos, pageable, 
            pedidosPage.getTotalElements());

        return pedidosResumoModelPage;
    }

    @GetMapping("/{pedido-id}")
    public PedidoModel buscar(@PathVariable("pedido-id") Long id) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(id);

        return pedidoModelMapper.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PedidoModel salvar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {
            Pedido pedido = pedidoInputDemapper.toDomainObject(pedidoInput);

            Usuario usuario = new Usuario();
            usuario.setId(1L);

            pedido.setCliente(usuario);

            return pedidoModelMapper.toModel(emissaoPedidoService.emitir(pedido));

        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
