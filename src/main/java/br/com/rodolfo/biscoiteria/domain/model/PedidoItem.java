package br.com.rodolfo.biscoiteria.domain.model;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "pedido_item")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    @Column(nullable = false)
    private BigDecimal precoCompraProduto;

    @Column(nullable = false)
    private BigDecimal lucro;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public void calcularValorTotal() {
        BigDecimal precoProduto = Optional.ofNullable(getPreco())
            .orElse(BigDecimal.ZERO);

        BigDecimal qtd = Optional.ofNullable(getQuantidade())
            .map(BigDecimal::valueOf)
            .orElse(BigDecimal.ZERO);

        setPrecoTotal(precoProduto.multiply(qtd));
    }

    public void calcularLucroTotal() {
        BigDecimal precoCompra = Optional.ofNullable(getPrecoCompraProduto())
            .orElse(BigDecimal.ZERO);

        BigDecimal precoVenda = Optional.ofNullable(getPreco())
            .orElse(BigDecimal.ZERO);

        BigDecimal qtd = Optional.ofNullable(getQuantidade())
            .map(BigDecimal::valueOf)
            .orElse(BigDecimal.ZERO);

        BigDecimal lucroParcial = precoVenda.subtract(precoCompra);

        setLucro(lucroParcial.multiply(qtd));
    }
}
