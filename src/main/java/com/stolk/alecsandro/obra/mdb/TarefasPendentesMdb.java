//package com.stolk.alecsandro.obra.mdb;
//
//import com.stolk.alecsandro.obra.business.AgendamentolBusiness;
//import com.stolk.alecsandro.obra.interceptor.Logger;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.inject.Inject;
//import javax.jms.JMSException;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
//@Logger
//@MessageDriven(activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationLookup",
//                propertyValue = "java:/jms/queue/EmailQueue"),
//        @ActivationConfigProperty(propertyName = "destinationType",
//                propertyValue = "javax.jms.Queue")
//})
//public class TarefasPendentesMdb implements MessageListener {
//
//    @Inject
//    private AgendamentolBusiness business;
//
//    @Override
//    public void onMessage(javax.jms.Message message) {
//        try {
//            TextMessage textMessage = (TextMessage) message;
//            business.enviarTarefasFazer(textMessage.getText());
//        } catch (JMSException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}