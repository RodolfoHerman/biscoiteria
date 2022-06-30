package br.com.rodolfo.biscoiteria.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemModel {

    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal preco;
    private BigDecimal precoTotal;
}
