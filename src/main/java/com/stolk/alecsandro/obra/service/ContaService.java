package com.stolk.alecsandro.obra.service;

import com.stolk.alecsandro.obra.banco.ContaDao;
import com.stolk.alecsandro.obra.modelo.Conta;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContaService {

    @Inject
    private ContaDao dao;

    public void cadastrar(Conta conta) {
        dao.cadastrar(conta);
    }

    public void editar(Conta conta) {
        dao.editar(conta);
    }

    public void editar(Long id, Conta conta) {
        dao.editar(id, conta);
    }

    public void excluir(Conta conta) {
        dao.excluir(conta);
    }

    public void excluir(Long id) {
        dao.excluir(id);
    }
}
