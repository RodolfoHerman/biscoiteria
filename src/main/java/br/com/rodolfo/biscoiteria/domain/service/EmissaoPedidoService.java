package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.exception.PedidoNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.Pedido;
import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;
import br.com.rodolfo.biscoiteria.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);

        pedido.calcularValorTotal();
        pedido.calcularLucroTotal();
        pedido.atribuirPedidoAosItens();

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(pedido.getCliente().getId());
        pedido.setCliente(usuario);

        pedido.getItens().forEach(item -> {
            Produto produto = cadastroProdutoService.buscarOuFalhar(item.getProduto().getId());
            produto.verificarAtualizarQuantidadeEstoque(item.getQuantidade());

            item.setProduto(produto);
            item.setPreco(produto.getPrecoVenda());
            item.setPrecoCompraProduto(produto.getPrecoCompra());

            item.calcularValorTotal();
            item.calcularLucroTotal();
        });
    }

    public Pedido buscarOuFalhar(Long id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }
}
