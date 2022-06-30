package br.com.rodolfo.biscoiteria.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {

    private Long id;
    private LocalDate dataCriacao;
    private LocalDate dataEntrega;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataPagamento;
    private BigDecimal precoTotal;
    private UsuarioResumoModel cliente;
    private String status;
    private String observacao;
}
