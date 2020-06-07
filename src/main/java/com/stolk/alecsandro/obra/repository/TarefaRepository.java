package com.stolk.alecsandro.obra.repository;

import com.stolk.alecsandro.obra.banco.TarefaDao;
import com.stolk.alecsandro.obra.modelo.Tarefa;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class TarefaRepository implements Serializable {

    @Inject
    private TarefaDao dao;

    public List<Tarefa> buscar() {
        return dao.buscar();
    }

    public List<Tarefa> buscarNaoFeitos(){
        return dao.buscarNaoFeitos();
    }

    public Tarefa buscar(Long id) {
        return dao.buscar(id);
    }

    public List<Tarefa> buscarTitulo(String nome) {
        return dao.buscarNome(nome);
    }

    public Long buscarQuantidade() {
        return dao.buscarQuantidade();
    }

}
