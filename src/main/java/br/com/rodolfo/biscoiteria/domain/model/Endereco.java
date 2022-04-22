package br.com.rodolfo.biscoiteria.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Endereco {

    @Column(name = "endereco_bairro", nullable = false)
    private String bairro;
    
    @Column(name = "endereco_rua", nullable = false)
    private String rua;

    @Column(name = "endereco_numero", nullable = false)
    private String numero;
    
    @Column(name = "endereco_complemento")
    private String complemento;
}
