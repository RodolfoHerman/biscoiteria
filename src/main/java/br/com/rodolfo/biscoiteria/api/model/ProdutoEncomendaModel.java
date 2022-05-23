package br.com.rodolfo.biscoiteria.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoEncomendaModel {
    private Long id;

    private Integer quantidade;

    private BigDecimal precoCompra;

    private OffsetDateTime dataCadastro;

    private ProdutoIdModel produto;
}
