package br.com.rodolfo.biscoiteria.domain.service;

import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public interface FotoStorageService {

    InputStream recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoExistente, NovaFoto novaFoto) {
        this.armazenar(novaFoto);

        if(StringUtils.isNoneBlank(nomeArquivoExistente)) {
            this.remover(nomeArquivoExistente);
        }
    }

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID().toString() + "_" + nomeOriginal;
    }

    @Getter
    @Setter
    @Builder
    class NovaFoto {

        private String nomeArquivo;
        private InputStream inputStream;
    }
}
