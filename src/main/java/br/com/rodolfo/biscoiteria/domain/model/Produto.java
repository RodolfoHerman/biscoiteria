package br.com.rodolfo.biscoiteria.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.rodolfo.biscoiteria.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @NotNull(groups = Groups.ProdutoId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    private Integer quantidadeEstoque;

    @Column(columnDefinition = "date")
    private LocalDate dataEncomenda;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal precoVenda;

    @Positive
    private BigDecimal precoCompra;

    @Column(nullable = false)
    private boolean ativo;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class, to = Groups.CategoriaId.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private ProdutoCategoria categoria;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoEncomenda> encomendas = new ArrayList<>();

    public void atualizarEstoque(Integer quantidade) {
        setDataEncomenda(LocalDate.now());

        Integer quantidadeAtual = Optional.ofNullable(getQuantidadeEstoque())
            .orElse(0);

        setQuantidadeEstoque(quantidadeAtual + quantidade);
    }

    public boolean quantidadeInsuficienteEmEstoque(Integer quantidadeSolicitada) {
        return (getQuantidadeEstoque() - quantidadeSolicitada) < 0;
    }
}
