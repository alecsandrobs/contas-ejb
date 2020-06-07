package com.stolk.alecsandro.obra.business;

import com.stolk.alecsandro.obra.interceptor.Logger;
import com.stolk.alecsandro.obra.modelo.Agendamento;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;

@Logger
@Stateless
public class AgendamentolBusiness {

    @Resource(lookup = "java:jboss/mail/gmail")
    private Session sessaoEmail;
    private static final String FROM_EMAIL = "teste.alecsandro.stolk@gmail.com";
    private static final String FROM_NAME = "Robô Carteiro";
    private static final String TO = "alecsandro.stolk@gmail.com, alecsandro.stolk@hotmail.com";
    private static final String TAREFAS_PENDENTES = "Tarefas pendentes";
    private static final String LANCAMENTOS_PENDENTES = "Lançamentos pendentes";

    public void enviar(Agendamento agendamento) {
        MimeMessage message = new MimeMessage(sessaoEmail);
        try {
            message.setFrom(new InternetAddress(FROM_EMAIL, FROM_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(agendamento.getEmail()));
            message.setSubject(agendamento.getAssunto());
            message.setContent(agendamento.getMensagem(), MediaType.TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
