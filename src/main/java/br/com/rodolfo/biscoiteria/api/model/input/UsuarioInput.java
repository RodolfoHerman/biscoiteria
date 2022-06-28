package br.com.rodolfo.biscoiteria.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone; 

    private EnderecoInput endereco;

    private String email;
}
