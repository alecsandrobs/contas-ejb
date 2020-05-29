package com.stolk.alecsandro.obra.repository;

import com.stolk.alecsandro.obra.banco.LancamentoDao;
import com.stolk.alecsandro.obra.modelo.Lancamento;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class LancamentoRepository {

    @Inject
    private LancamentoDao dao;

    public List<Lancamento> buscar() {
        return dao.buscar();
    }

    public Lancamento buscar(Long id) {
        return dao.buscar(id);
    }

    public Long buscarQuantidade() {
        return dao.buscarQuantidade();
    }

    public Double buscarSoma(Lancamento.TipoLancamento tipo, boolean pago) {
        return dao.buscarSoma(tipo, pago);
    }

}
