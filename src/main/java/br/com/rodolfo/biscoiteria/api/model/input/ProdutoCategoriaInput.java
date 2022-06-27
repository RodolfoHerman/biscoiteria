package br.com.rodolfo.biscoiteria.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCategoriaInput {

    @NotNull
    private String nome;
}
