package br.com.rodolfo.biscoiteria.domain.model.enums;

import lombok.Getter;

@Getter
public enum FormaPagamento {

    NAO_PAGO("Não pago"),
    DINHEIRO("Dinheiro"),
    PIX("Pix");

    private String descricao;

    private FormaPagamento(String descricao) {
        this.descricao = descricao;
    }
}
