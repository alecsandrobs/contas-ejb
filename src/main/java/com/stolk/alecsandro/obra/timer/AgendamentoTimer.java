package com.stolk.alecsandro.obra.timer;

import com.stolk.alecsandro.obra.modelo.Agendamento;
import com.stolk.alecsandro.obra.repository.AgendamentoRepository;
import com.stolk.alecsandro.obra.service.AgendamentoService;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.List;

@Singleton
public class AgendamentoTimer {

    @Inject
    private AgendamentoRepository repository;

    @Inject
    private AgendamentoService service;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/EmailQueue")
    private Queue queue;

    @Schedule(hour = "*", minute = "*")
    public void enviarEmailsAgendados() {
        List<Agendamento> agendamentos = repository.buscarNaoEnviados();
        agendamentos.stream().forEach(agendamento -> {
            context.createProducer().send(queue, agendamento);
            service.marcarEnviado(agendamento);
        });
    }

}
