package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Conta;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class ContaDao implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public List<Conta> buscar() {
        CriteriaQuery<Conta> query = em.getCriteriaBuilder().createQuery(Conta.class);
        query.select(query.from(Conta.class));
        List<Conta> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public Conta buscar(Long id) {
        Conta conta = em.find(Conta.class, id);
        return conta;
    }

    public Long buscarQuantidade() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Conta.class)));
        Long result = em.createQuery(query).getSingleResult();
        return result;
    }

    public void cadastrar(Conta conta) {
        em.persist(conta);
    }

    public void editar(Conta conta) {
        em.merge(conta);
    }

    public void editar(Long id, Conta conta) {
        conta.setId(id);
        em.merge(conta);
    }

    public void excluir(Conta conta) {
        em.remove(conta);
    }

    public void excluir(Long id) {
        Conta conta = this.buscar(id);
        em.remove(conta);
    }
}
