package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Agendamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class AgendamentoDao implements Serializable {

    @PersistenceContext
    private EntityManager em;

    /* ================================================= */
    public void cadastrar(Agendamento agendamento) {
        em.persist(agendamento);
    }

    public void editar(Agendamento agendamento) {
        em.merge(agendamento);
    }

    public void excluir(Agendamento agendamento) {
        em.remove(agendamento);
    }

    public List<Agendamento> buscar() {
        CriteriaQuery<Agendamento> query = em.getCriteriaBuilder().createQuery(Agendamento.class);
        query.select(query.from(Agendamento.class));
        List<Agendamento> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public Agendamento buscar(Long id) {
        Agendamento agendamento = em.find(Agendamento.class, id);
        return agendamento;
    }

    public Long buscarQuantidade() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Agendamento.class)));
        Long result = em.createQuery(query).getSingleResult();
        return result;
    }

    public List<Agendamento> buscarNaoEnviados(String email) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Agendamento> query = builder.createQuery(Agendamento.class);
        Root<Agendamento> colunas = query.from(Agendamento.class);

        Expression filtroNaoEnviado = builder.equal(colunas.get("enviado"), false);
        Expression filtroEmail = builder.like(colunas.get("email"), email);
        if (email == null || email.trim().isEmpty()) {
            query.where(filtroNaoEnviado);
        } else {
            query.where(builder.and(filtroNaoEnviado, filtroEmail));
        }
        List<Agendamento> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public List<Agendamento> buscarNaoEnviados() {
        return buscarNaoEnviados(null);
    }
    /* ================================================= */

    public List<Agendamento> listarNaoEnviados(String email) {
        Query query = em.createQuery("select a from Agendamento a where a.email = :pEmail and a.enviado = false", Agendamento.class);
        query.setParameter("pEmail", email);
        return query.getResultList();
    }

    public List<Agendamento> listarNaoEnviados() {
        Query query = em.createQuery("select a from Agendamento a where a.enviado = false", Agendamento.class);
        return query.getResultList();
    }
}
