package com.stolk.alecsandro.obra.repository;

import com.stolk.alecsandro.obra.banco.ContatoDao;
import com.stolk.alecsandro.obra.modelo.Contato;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ContatoRepository {

    @Inject
    private ContatoDao dao;

    public List<Contato> buscar() {
        return dao.buscar();
    }

    public Contato buscar(Long id) {
        return dao.buscar(id);
    }

    public List<Contato> buscarNome(String nome) {
        return dao.buscarNome(nome);
    }

    public Long buscarQuantidade() {
        return dao.buscarQuantidade();
    }
}
