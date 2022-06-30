package br.com.rodolfo.biscoiteria.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {

    @Valid
    @NotNull
    @Size(min = 1)
    private List<PedidoItemInput> itens;
}
