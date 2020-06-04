package com.stolk.alecsandro.obra.mdb;

import com.stolk.alecsandro.obra.business.AgendamentolBusiness;
import com.stolk.alecsandro.obra.interceptor.Logger;
import com.stolk.alecsandro.obra.modelo.Agendamento;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageListener;

@Logger
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/EmailQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class EmailMdb implements MessageListener {

    @Inject
    private AgendamentolBusiness business;

    @Override
    public void onMessage(javax.jms.Message message) {
        try {
            Agendamento agendamento = message.getBody(Agendamento.class);
            business.enviar(agendamento);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}