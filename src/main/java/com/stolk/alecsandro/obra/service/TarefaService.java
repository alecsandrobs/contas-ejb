package com.stolk.alecsandro.obra.service;

import com.stolk.alecsandro.obra.banco.TarefaDao;
import com.stolk.alecsandro.obra.modelo.Tarefa;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TarefaService {

    @Inject
    private TarefaDao dao;

    public void cadastrar(Tarefa tarefa) {
        tarefa.setFeito(false);
        dao.cadastrar(tarefa);
    }

    public void marcarFeito(Tarefa tarefa) {
        tarefa.setFeito(true);
        dao.editar(tarefa);
    }

    public void editar(Tarefa tarefa) {
        dao.editar(tarefa);
    }

    public void editar(Long id, Tarefa tarefa) {
        dao.editar(id, tarefa);
    }

    public void excluir(Tarefa tarefa) {
        dao.excluir(tarefa);
    }

    public void excluir(Long id) {
        dao.excluir(id);
    }
}
