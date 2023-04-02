package br.com.rodolfo.biscoiteria.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import br.com.rodolfo.biscoiteria.domain.event.PedidoCanceladoEvent;
import br.com.rodolfo.biscoiteria.domain.event.PedidoEntregueEvent;
import br.com.rodolfo.biscoiteria.domain.event.PedidoPagoEvent;
import br.com.rodolfo.biscoiteria.domain.exception.NegocioException;
import br.com.rodolfo.biscoiteria.domain.model.enums.FormaPagamento;
import br.com.rodolfo.biscoiteria.domain.model.enums.PedidoStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Pedido extends AbstractAggregateRoot<Pedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @CreationTimestamp
    @Column(columnDefinition = "date")
    private LocalDate dataCriacao;

    private LocalDate dataEntrega;

    private OffsetDateTime dataCancelamento;

    private OffsetDateTime dataPagamento;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    @Column(nullable = false)
    private BigDecimal lucroTotal;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PedidoStatus status = PedidoStatus.PENDENTE;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento = FormaPagamento.NAO_PAGO;

    private String observacao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoItem> itens = new ArrayList<>();

    public void atribuirPedidoAosItens() {
        getItens().forEach(item -> item.setPedido(this));
    }

    public void calcularValorTotal() {
        BigDecimal total = getItens().stream()
            .map(PedidoItem::getPrecoTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        setPrecoTotal(total);
    }

    public void calcularLucroTotal() {
        BigDecimal total = getItens().stream()
            .map(PedidoItem::getLucro)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        setLucroTotal(total);
    }

    public void entregar() {
        setDataEntrega(LocalDate.now());

        registerEvent(new PedidoEntregueEvent(this));
    }

    public void cancelar() {
        setDataCancelamento(OffsetDateTime.now());

        registerEvent(new PedidoCanceladoEvent(this));
    }

    public void pagar() {
        setDataPagamento(OffsetDateTime.now());

        registerEvent(new PedidoPagoEvent(this));
    }

    public void setStatus(PedidoStatus novoStatus) {
        if(getStatus().naoPodeAlterarPara(novoStatus)) {
            throw new NegocioException(
                String.format(
                    "Status do pedido %d não pode ser alterado de %s para %s",
                    getId(),
                    getStatus().getDescricao(),
                    novoStatus.getDescricao()));
        }

        this.status = novoStatus;
    }
}
