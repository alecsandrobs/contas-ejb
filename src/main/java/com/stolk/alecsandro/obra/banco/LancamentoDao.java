package com.stolk.alecsandro.obra.banco;

import com.stolk.alecsandro.obra.modelo.Lancamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

public class LancamentoDao implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public List<Lancamento> buscar() {
        CriteriaQuery<Lancamento> query = em.getCriteriaBuilder().createQuery(Lancamento.class);
        query.select(query.from(Lancamento.class));
        List<Lancamento> lista = em.createQuery(query).getResultList();
        return lista;
    }

    public List<Lancamento> buscar(boolean efetivado) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Lancamento> query = builder.createQuery(Lancamento.class);
        Root<Lancamento> colunas = query.from(Lancamento.class);
        Path<Object> pagamento = colunas.get("pagamento");
        Expression filtroEfetivado = efetivado ? builder.isNotNull(pagamento) : builder.isNull(pagamento);
        List<Lancamento> lancamentos = em.createQuery(query.where(filtroEfetivado)).getResultList();
        return lancamentos;
    }

    public Lancamento buscar(Long id) {
        Lancamento lancamento = em.find(Lancamento.class, id);
        return lancamento;
    }

    public Long buscarQuantidade() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Lancamento.class)));
        Long result = em.createQuery(query).getSingleResult();
        return result;
    }

    public Double buscarSoma(Lancamento.TipoLancamento tipo, boolean pago) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Double> query = builder.createQuery(Double.class);
        Root<Lancamento> colunas = query.from(Lancamento.class);
        Expression filtraTipo = builder.equal(colunas.get("tipo"), tipo);
        Expression filtraEfetivado = builder.isNotNull(colunas.get("pagamento"));
        if (pago) {
            query.where(builder.and(filtraTipo, filtraEfetivado));
        } else {
            query.where(filtraTipo);
        }
        Double valor = em.createQuery(query.select(builder.sum(colunas.get("valor")))).getSingleResult();
        return valor;
    }

    public void cadastrar(Lancamento lancamento) {
        em.persist(lancamento);
    }

    public void editar(Lancamento lancamento) {
        em.merge(lancamento);
    }

    public void editar(Long id, Lancamento lancamento) {
        lancamento.setId(id);
        em.merge(lancamento);
    }

    public void excluir(Lancamento lancamento) {
        em.remove(lancamento);
    }

    public void excluir(Long id) {
        Lancamento lancamento = buscar(id);
        em.remove(lancamento);
    }

    public List<Lancamento> buscarPaginado(int firstResult, int maxResults) {
        CriteriaQuery<Lancamento> query = em.getCriteriaBuilder().createQuery(Lancamento.class);
        query.select(query.from(Lancamento.class));
        List<Lancamento> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        return lista;
    }
}
