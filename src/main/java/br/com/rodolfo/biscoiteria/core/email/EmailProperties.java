package br.com.rodolfo.biscoiteria.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties("biscoiteria.email")
public class EmailProperties {

    @NotNull
    private String remetente;

    private TipoEmail impl = TipoEmail.FAKE;

    public enum TipoEmail {
        FAKE, SMTP;
    }
}
