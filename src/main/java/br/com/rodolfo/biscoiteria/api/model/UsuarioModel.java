package br.com.rodolfo.biscoiteria.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    private Long id;
    private String nome;
    private String telefone; 
    private EnderecoModel endereco;
    private String email;
}
