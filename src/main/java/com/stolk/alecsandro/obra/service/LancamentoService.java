package com.stolk.alecsandro.obra.service;

import com.stolk.alecsandro.obra.banco.LancamentoDao;
import com.stolk.alecsandro.obra.modelo.Lancamento;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LancamentoService {

    @Inject
    private LancamentoDao dao;

    public void cadastrar(Lancamento lancamento) {
        dao.cadastrar(lancamento);
    }

    public void editar(Lancamento lancamento) {
        dao.editar(lancamento);
    }

    public void editar(Long id, Lancamento lancamento) {
        dao.editar(id, lancamento);
    }

    public void excluir(Lancamento lancamento) {
        dao.excluir(lancamento);
    }

    public void excluir(Long id) {
        dao.excluir(id);
    }
}
