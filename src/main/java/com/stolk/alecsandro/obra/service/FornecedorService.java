package com.stolk.alecsandro.obra.service;

import com.stolk.alecsandro.obra.banco.FornecedorDao;
import com.stolk.alecsandro.obra.modelo.Fornecedor;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FornecedorService {

    @Inject
    private FornecedorDao dao;

    public void cadastrar(Fornecedor fornecedor) {
        dao.cadastrar(fornecedor);
    }

    public void editar(Fornecedor fornecedor) {
        dao.editar(fornecedor);
    }

    public void editar(Long id, Fornecedor fornecedor) {
        dao.editar(id, fornecedor);
    }

    public void excluir(Fornecedor fornecedor) {
        dao.excluir(fornecedor);
    }

    public void excluir(Long id) {
        dao.excluir(id);
    }
}
