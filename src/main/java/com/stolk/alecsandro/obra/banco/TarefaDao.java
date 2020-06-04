package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.recurso.Tarefa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class TarefaDao implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void cadastrar(Tarefa tarefa) {
        em.persist(tarefa);
    }

    public void editar(Tarefa tarefa) {
        em.merge(tarefa);
    }

    public void editar(Long id, Tarefa tarefa) {
        tarefa.setId(id);
        em.merge(tarefa);
    }

    public void excluir(Tarefa tarefa) {
        em.remove(tarefa);
    }

    public void excluir(Long id) {
        Tarefa tarefa = buscar(id);
        em.remove(tarefa);
    }

    public List<Tarefa> buscar() {
        CriteriaQuery<Tarefa> query = em.getCriteriaBuilder().createQuery(Tarefa.class);
        query.select(query.from(Tarefa.class));
        List<Tarefa> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public Tarefa buscar(Long id) {
        Tarefa tarefa = em.find(Tarefa.class, id);
        return tarefa;
    }

    public Long buscarQuantidade() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Tarefa.class)));
        Long result = em.createQuery(query).getSingleResult();
        return result;
    }

    public List<Tarefa> buscarNome(String nome) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tarefa> query = builder.createQuery(Tarefa.class);
        Root<Tarefa> colunas = query.from(Tarefa.class);
        Expression filtroNome = builder.equal(colunas.get("titulo"), false);
        query.where(filtroNome);
        List<Tarefa> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public List<Tarefa> buscarNaoFeitos() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tarefa> query = builder.createQuery(Tarefa.class);
        Root<Tarefa> colunas = query.from(Tarefa.class);
        Expression filtroFeito = builder.isFalse(colunas.get("feito"));
        List<Tarefa> lista = em.createQuery(query.where(filtroFeito)).getResultList();
        return lista;
    }
}
