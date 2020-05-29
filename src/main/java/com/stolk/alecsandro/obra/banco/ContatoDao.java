package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Contato;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class ContatoDao implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void cadastrar(Contato contato) {
        em.persist(contato);
    }

    public void editar(Contato contato) {
        em.merge(contato);
    }

    public void editar(Long id, Contato contato) {
        contato.setId(id);
        em.merge(contato);
    }

    public void excluir(Contato contato) {
        em.remove(contato);
    }

    public void excluir(Long id) {
        Contato contato = buscar(id);
        em.remove(contato);
    }

    public List<Contato> buscar() {
        CriteriaQuery<Contato> query = em.getCriteriaBuilder().createQuery(Contato.class);
        query.select(query.from(Contato.class));
        List<Contato> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public Contato buscar(Long id) {
        Contato contato = em.find(Contato.class, id);
        return contato;
    }

    public Long buscarQuantidade() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Contato.class)));
        Long result = em.createQuery(query).getSingleResult();
        return result;
    }

    public List<Contato> buscarNome(String nome) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Contato> query = builder.createQuery(Contato.class);
        Root<Contato> colunas = query.from(Contato.class);
        Expression filtroNome = builder.like(colunas.get("nome"), nome);
        query.where(filtroNome);
        List<Contato> lista = em.createQuery(query).getResultList();
        return lista;
    }
}
