package br.com.rodolfo.biscoiteria.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;

import br.com.rodolfo.biscoiteria.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoEncomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal precoCompra;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    @Transient
    private BigDecimal precoVenda;

    @Transient
    private boolean atualizarEstoque;

    @CreationTimestamp
    @Column(columnDefinition = "date")
    private LocalDate dataCadastro;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class, to = Groups.ProdutoId.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public void calcularValorTotal() {
        BigDecimal preco = Optional.ofNullable(getPrecoCompra())
            .orElse(BigDecimal.ZERO);

        BigDecimal qtd = Optional.ofNullable(getQuantidade())
            .map(BigDecimal::valueOf)
            .orElse(BigDecimal.ZERO);

        setPrecoTotal(preco.multiply(qtd));
    }
}
