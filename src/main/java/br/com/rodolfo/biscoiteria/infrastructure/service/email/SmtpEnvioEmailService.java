package br.com.rodolfo.biscoiteria.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.com.rodolfo.biscoiteria.core.email.EmailProperties;
import br.com.rodolfo.biscoiteria.domain.service.EnvioEmailService;
import freemarker.template.Configuration;


public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            String corpo = processarTemplate(mensagem);

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(mensagem.getDestinatarios().toArray(String[]::new));
            helper.setSubject(mensagem.getAssunto());
            helper.setText(corpo, Boolean.TRUE);

            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            throw new EmailException("Não foi possível enviar e-mail.", ex);
        }
    }

    protected String processarTemplate(Mensagem mensagem) {
        try {
            var template = freemarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(
                template, mensagem.getVariaveis());

        } catch (Exception ex) {
            throw new EmailException("Não foi possível montar o template do e-mail.", ex);
        }
    }
}
