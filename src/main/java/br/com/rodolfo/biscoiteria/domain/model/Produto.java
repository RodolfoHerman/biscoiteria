package br.com.rodolfo.biscoiteria.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    // @Column(nullable = false)
    // private Integer quantidadeEstoque;

    @Column(nullable = false)
    private BigDecimal precoVenda;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private ProdutoCategoria categoria;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoEncomenda> encomendas = new ArrayList<>();
}
