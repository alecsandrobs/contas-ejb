package com.stolk.alecsandro.obra.service;

import com.stolk.alecsandro.obra.banco.ContatoDao;
import com.stolk.alecsandro.obra.modelo.Contato;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContatoService {

    @Inject
    private ContatoDao dao;

    public void cadastrar(Contato contato) {
        dao.cadastrar(contato);
    }

    public void editar(Contato contato) {
        dao.editar(contato);
    }

    public void editar(Long id, Contato contato) {
        dao.editar(id, contato);
    }

    public void excluir(Contato contato) {
        dao.excluir(contato);
    }

    public void excluir(Long id) {
        dao.excluir(id);
    }
}
