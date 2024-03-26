package com.lufecrx.sistema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lufecrx.sistema.entidades.Pagamento;

public class PagamentoDAO {

    public static void criar(Pagamento pagamento, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(pagamento);
        entityManager.getTransaction().commit();
    }

    public static List<Pagamento> retornarTodos(EntityManager entityManager) {
        String jpql = "Select p FROM Pagamento p";

        TypedQuery<Pagamento> query = entityManager.createQuery(jpql, Pagamento.class);
        List<Pagamento> pagamentos = query.getResultList();

        return pagamentos;
    }

    public static Pagamento retornarPelaID(String id, EntityManager entityManager) {
        String jpql = "Select p FROM Pagamento p WHERE p.idFatura = :id";

        TypedQuery<Pagamento> query = entityManager.createQuery(jpql, Pagamento.class);
        query.setParameter("id", id);

        Pagamento pagamento = query.getSingleResult();

        return pagamento;
    }

    public static void atualizar(Pagamento pagamento, EntityManager entityManager) {
        String jpql = "SELECT p FROM Pagamento p WHERE p.idFatura = :id";

        TypedQuery<Pagamento> query = entityManager.createQuery(jpql, Pagamento.class);
        query.setParameter("id", pagamento.getFatura().getIdFatura());

        Pagamento pagamentoAtualizado = query.getSingleResult();

        pagamentoAtualizado.setValor(pagamento.getValor());
        pagamentoAtualizado.setData(pagamento.getData());

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoAtualizado);
        entityManager.getTransaction().commit();
    }

    public static void deletar(String idFatura, EntityManager entityManager) {
        String jpql = "SELECT p FROM Pagamento p WHERE p.idFatura = :id";

        TypedQuery<Pagamento> query = entityManager.createQuery(jpql, Pagamento.class);
        query.setParameter("id", idFatura);

        Pagamento pagamento = query.getSingleResult();

        entityManager.getTransaction().begin();
        entityManager.remove(pagamento);
        entityManager.getTransaction().commit();
    }

}