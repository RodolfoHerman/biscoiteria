package br.com.rodolfo.biscoiteria.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFotoModel {

    private String nomeArquivo;
    private String contentType;
    private Long tamanho;
}
