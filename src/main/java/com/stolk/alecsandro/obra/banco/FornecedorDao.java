package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class FornecedorDao implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void cadastrar(Fornecedor fornecedor) {
        em.persist(fornecedor);
    }

    public void editar(Fornecedor fornecedor) {
        em.merge(fornecedor);
    }

    public void editar(Long id, Fornecedor fornecedor) {
        fornecedor.setId(id);
        em.merge(fornecedor);
    }

    public void excluir(Fornecedor fornecedor) {
        em.remove(fornecedor);
    }

    public void excluir(Long id) {
        Fornecedor fornecedor = buscar(id);
        em.remove(fornecedor);
    }

    public List<Fornecedor> buscar() {
        CriteriaQuery<Fornecedor> query = em.getCriteriaBuilder().createQuery(Fornecedor.class);
        query.select(query.from(Fornecedor.class));
        List<Fornecedor> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public Fornecedor buscar(Long id) {
        Fornecedor fornecedor = em.find(Fornecedor.class, id);
        return fornecedor;
    }

    public Long buscarQuantidade() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Fornecedor.class)));
        Long result = em.createQuery(query).getSingleResult();
        return result;
    }

    public List<Fornecedor> buscarNome(String nome) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Fornecedor> query = builder.createQuery(Fornecedor.class);
        Root<Fornecedor> colunas = query.from(Fornecedor.class);
        Expression filtroNome = builder.equal(colunas.get("nome"), false);
        query.where(filtroNome);
        List<Fornecedor> lista = em.createQuery(query).getResultList();
        return lista;
    }
}
