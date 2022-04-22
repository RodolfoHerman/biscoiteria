package br.com.rodolfo.biscoiteria.domain.model.enums;

import lombok.Getter;

@Getter
public enum FormaPagamento {

    DINHEIRO("Dinheiro"),
    PIX("Pix");

    private String descrico;

    private FormaPagamento(String descricao) {
        this.descrico = descricao;
    }
}
