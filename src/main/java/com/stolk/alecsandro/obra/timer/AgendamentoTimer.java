package com.stolk.alecsandro.obra.timer;

import com.stolk.alecsandro.obra.modelo.Agendamento;
import com.stolk.alecsandro.obra.modelo.Lancamento;
import com.stolk.alecsandro.obra.recurso.Tarefa;
import com.stolk.alecsandro.obra.repository.LancamentoRepository;
import com.stolk.alecsandro.obra.repository.TarefaRepository;
import com.stolk.alecsandro.obra.util.Util;

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

    private static final String EMAILS = "alecsandro.stolk@gmail.com, alecsandro.stolk@hotmail.com, talitadanielskipereira@gmail.com, thatinhadp@hotmail.com";

    @Inject
    private LancamentoRepository lancamentoRepository;

    @Inject
    private TarefaRepository tarefaRepository;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/EmailQueue")
    private Queue queue;

    private Agendamento agendamento = new Agendamento();

    /*@Schedule(hour = "15", minute = "0")
    public void enviarEmailLancamentosNaoPago() {
        String message = "| Data       | Fornecedor                               | Valor         |";
        List<Lancamento> lancamentos = lancamentoRepository.buscarNaoEfetivado();
        for (Lancamento lancamento : lancamentos) {
            String data = Util.dataTxtBr(lancamento.getData());
            String fornecedor = Util.completaEsquerda(lancamento.getFornecedor().getNome(), 40);
            String valor = Util.completaDireita(Util.emReal(lancamento.getValor()), 13);
            message += String.format("\n| %s | %s  | %s |", data, fornecedor, valor);
        }
        context.createProducer().send(queue, message);
    }*/

    @Schedule(hour = "22", minute = "25")
    public void enviarEmailLancamentosNaoPago() {
        String conteudo = "<table><thead><tr><th>Data</th><th>Fornecedor</th><th>Tipo</th><th>Valor</th></tr></thead><tbody>";
        List<Lancamento> lancamentos = lancamentoRepository.buscarNaoEfetivado();
        for (Lancamento lancamento : lancamentos) {
            String data = Util.dataTxtBr(lancamento.getData());
            String fornecedor = Util.completaEsquerda(lancamento.getFornecedor().getNome(), 40);
            String tipo = lancamento.getTipo().getValor();
            String valor = Util.completaDireita(Util.emReal(lancamento.getValor()), 13);
            conteudo += String.format("\n<tr><td>%s</td><td>%s</td><td>%s</td><td style='text-align:right'>%s</td></tr>", data, fornecedor, tipo, valor);
        }
        conteudo += "</tbody></table>";
        this.agendamento.setEmail(EMAILS);
        this.agendamento.setAssunto("Contas a pagar");
        this.agendamento.setMensagem(conteudo);
        context.createProducer().send(queue, this.agendamento);
    }

    @Schedule(hour = "22", minute = "30")
    public void enviarTarefasFazer() {
        String conteudo = "<ol>";
        List<Tarefa> tarefas = tarefaRepository.buscarNaoFeitos();
        for (Tarefa tarefa : tarefas) {
            conteudo += String.format("<li>%s\n<p>%s</p></li>", tarefa.getTitulo(), tarefa.getDescricao());
        }
        conteudo += "</ol>";
        this.agendamento.setEmail(EMAILS);
        this.agendamento.setAssunto("Tarefas pendentes");
        this.agendamento.setMensagem(conteudo);
        context.createProducer().send(queue, this.agendamento);
    }

}
