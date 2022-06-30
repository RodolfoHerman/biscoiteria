package br.com.rodolfo.biscoiteria.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

    private Long id;
    private LocalDate dataCriacao;
    private LocalDate dataEntrega;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataPagamento;
    private BigDecimal precoTotal;
    private UsuarioModel cliente;
    private String status;
    private String formaPagamento;
    private String observacao;
    private List<PedidoItemModel> itens;
}
