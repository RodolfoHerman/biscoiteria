package br.com.rodolfo.biscoiteria.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Endereco {

    @Column(name = "endereco_bairro")
    private String bairro;
    
    @Column(name = "endereco_rua")
    private String rua;

    @Column(name = "endereco_numero")
    private String numero;
    
    @Column(name = "endereco_complemento")
    private String complemento;
}
