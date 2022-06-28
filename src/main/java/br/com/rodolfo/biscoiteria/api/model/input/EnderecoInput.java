package br.com.rodolfo.biscoiteria.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
}
