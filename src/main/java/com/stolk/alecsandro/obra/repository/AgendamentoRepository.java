package com.stolk.alecsandro.obra.repository;

import com.stolk.alecsandro.obra.banco.AgendamentoDao;
import com.stolk.alecsandro.obra.modelo.Agendamento;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class AgendamentoRepository {

    @Inject
    private AgendamentoDao dao;

    public List<Agendamento> buscar() {
        return dao.buscar();
    }

    public List<Agendamento> buscarNaoEnviados() {
        return dao.buscarNaoEnviados();
    }
}
