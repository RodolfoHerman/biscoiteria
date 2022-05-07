package br.com.rodolfo.biscoiteria.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class EncomendaDTO {

    private Long id;

    private Integer quantidade;

    private BigDecimal precoCompra;

    private LocalDateTime dataCadastro;

    private Long idProduto;
}
