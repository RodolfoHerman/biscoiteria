package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.exception.ProdutoEncomendaNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoEncomendaRepository;

@Service
public class CadastroProdutoEncomendaService {

    @Autowired
    private ProdutoEncomendaRepository produtoEncomendaRepository;

    @Transactional
    public ProdutoEncomenda salvar(ProdutoEncomenda produtoEncomenda) {
        produtoEncomenda.calcularValorTotal();

        if(produtoEncomenda.isAtualizarEstoque()) {
            produtoEncomenda.getProduto().atualizarEstoque(produtoEncomenda.getQuantidade());
        }

        return produtoEncomendaRepository.save(produtoEncomenda);
    }

    public ProdutoEncomenda buscarOuFalhar(Long encomendaId, Long produtoId) {
        return produtoEncomendaRepository.findByIdAndProduto_id(encomendaId, produtoId)
            .orElseThrow(() -> new ProdutoEncomendaNaoEncontradoException(encomendaId, produtoId));
    }
}
