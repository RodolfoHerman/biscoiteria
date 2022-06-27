package br.com.rodolfo.biscoiteria.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCategoriaIdInput {

    @NotNull
    private Long id;
}
