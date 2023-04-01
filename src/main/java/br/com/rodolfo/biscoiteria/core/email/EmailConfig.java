package br.com.rodolfo.biscoiteria.core.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rodolfo.biscoiteria.core.email.EmailProperties.TipoEmail;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService;
import br.com.rodolfo.biscoiteria.infrastructure.service.email.FakeEnvioEmailService;
import br.com.rodolfo.biscoiteria.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

    @Bean
    EnvioEmailService getEnvioEmailService(EmailProperties emailProperties) {
        if(TipoEmail.SMTP.equals(emailProperties.getImpl())) {
            return new SmtpEnvioEmailService();
        }

        return new FakeEnvioEmailService();
    }
}
