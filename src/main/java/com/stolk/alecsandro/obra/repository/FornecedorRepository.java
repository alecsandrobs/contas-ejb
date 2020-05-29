package com.stolk.alecsandro.obra.repository;

import com.stolk.alecsandro.obra.banco.FornecedorDao;
import com.stolk.alecsandro.obra.modelo.Fornecedor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class FornecedorRepository implements Serializable {

    @Inject
    private FornecedorDao dao;

    public List<Fornecedor> buscar() {
        return dao.buscar();
    }

    public Fornecedor buscar(Long id) {
        return dao.buscar(id);
    }

    public List<Fornecedor> buscarNome(String nome) {
        return dao.buscarNome(nome);
    }

    public Long buscarQuantidade() {
        return dao.buscarQuantidade();
    }

}
