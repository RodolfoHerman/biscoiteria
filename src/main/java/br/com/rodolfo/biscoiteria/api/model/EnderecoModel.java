package br.com.rodolfo.biscoiteria.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
}
