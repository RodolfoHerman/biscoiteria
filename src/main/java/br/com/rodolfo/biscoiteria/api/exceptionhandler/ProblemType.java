package br.com.rodolfo.biscoiteria.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    ERRO_DE_NEGOCIO("/erro-de-negocio", "Erro de negócio"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");

    private String uri;
    private String title;

    private ProblemType(String path, String title) {
        this.uri = "http://localhost:8080".concat(path);
        this.title = title;
    }
}
