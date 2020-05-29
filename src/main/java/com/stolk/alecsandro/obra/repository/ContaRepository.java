package com.stolk.alecsandro.obra.repository;

import com.stolk.alecsandro.obra.banco.ContaDao;
import com.stolk.alecsandro.obra.modelo.Conta;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ContaRepository {

    @Inject
    private ContaDao dao;

    public List<Conta> buscar() {
        return dao.buscar();
    }

    public Conta buscar(Long id) {
        return dao.buscar(id);
    }

    public Long buscarQuantidade() {
        return dao.buscarQuantidade();
    }
}
