package com.lufecrx.sistema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lufecrx.sistema.entidades.Fatura;

public class FaturaDAO {

    public static void criar(Fatura fatura, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(fatura);
        entityManager.getTransaction().commit();
    }

    public static List<Fatura> retornarTodos(EntityManager entityManager) {
        String jpql = "Select f FROM Fatura f";
        TypedQuery<Fatura> query = entityManager.createQuery(jpql, Fatura.class);
        return query.getResultList();
    }

    public static Fatura retornarPelaID(String id, EntityManager entityManager) {
        String jpql = "Select f FROM Fatura f WHERE f.idFatura = :id";

        TypedQuery<Fatura> query = entityManager.createQuery(jpql, Fatura.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    public static void atualizar(Fatura fatura, EntityManager entityManager) {
        String jpql = "SELECT f FROM Fatura f WHERE f.idFatura = :id";

        TypedQuery<Fatura> query = entityManager.createQuery(jpql, Fatura.class);
        query.setParameter("id", fatura.getIdFatura());
        
        Fatura faturaAtualizada = query.getSingleResult();

        faturaAtualizada.setValorPago(fatura.getValorPago());
        faturaAtualizada.setDivida(fatura.getDivida());
        faturaAtualizada.setQuitado(fatura.isQuitado());

        entityManager.getTransaction().begin();
        entityManager.persist(faturaAtualizada);
        entityManager.getTransaction().commit();
    }

    public static void deletar(String idFatura, EntityManager entityManager) {
        String jpql = "SELECT f FROM Fatura f WHERE f.idFatura = :id";

        TypedQuery<Fatura> query = entityManager.createQuery(jpql, Fatura.class);
        query.setParameter("id", idFatura);

        Fatura fatura = query.getSingleResult();
        
        entityManager.getTransaction().begin();
        entityManager.remove(fatura);
        entityManager.getTransaction().commit();
    }

}
