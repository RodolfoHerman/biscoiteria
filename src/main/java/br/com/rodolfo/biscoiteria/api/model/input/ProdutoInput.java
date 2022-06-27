package br.com.rodolfo.biscoiteria.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Positive
    private BigDecimal precoVenda;

    @NotNull
    private Boolean ativo;

    @Valid
    @NotNull
    private ProdutoCategoriaIdInput categoria;
}
