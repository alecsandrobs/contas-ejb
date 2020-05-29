package com.stolk.alecsandro.obra.business;

import com.stolk.alecsandro.obra.interceptor.Logger;
import com.stolk.alecsandro.obra.modelo.Agendamento;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Optional;

@Logger
@Stateless
public class AgendamentoBusiness implements Serializable {

    @Resource(lookup = "java:jboss/mail/AgendamentoMailSession")
    private Session sessaoEmail;

    private static String EMAIL_FROM = "mail.smtp.host";

    public void enviar(Agendamento agendamento) {
        try {
            MimeMessage mensagem = new MimeMessage(sessaoEmail);
            mensagem.setFrom(sessaoEmail.getProperty(EMAIL_FROM));
            mensagem.setRecipients(Message.RecipientType.TO, agendamento.getEmail());
            mensagem.setSubject(agendamento.getAssunto());
            mensagem.setText(Optional.ofNullable(agendamento.getMensagem()).orElse(""));
            Transport.send(mensagem);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}