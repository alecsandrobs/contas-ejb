package com.stolk.alecsandro.obra.service;

import com.stolk.alecsandro.obra.banco.AgendamentoDao;
import com.stolk.alecsandro.obra.exception.BusinessException;
import com.stolk.alecsandro.obra.modelo.Agendamento;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;

@Stateless
public class AgendamentoService {

    @Inject
    private AgendamentoDao dao;

    public void cadastrar(@Valid Agendamento agendamentoEmail) throws BusinessException {
        if (!dao.buscarNaoEnviados(agendamentoEmail.getEmail()).isEmpty()) {
            throw new BusinessException("Email já agendado.");
        }
        agendamentoEmail.setEnviado(false);
        dao.cadastrar(agendamentoEmail);
    }

    public void marcarEnviado(Agendamento agendamento) {
        agendamento.setEnviado(true);
        dao.editar(agendamento);
    }
}
