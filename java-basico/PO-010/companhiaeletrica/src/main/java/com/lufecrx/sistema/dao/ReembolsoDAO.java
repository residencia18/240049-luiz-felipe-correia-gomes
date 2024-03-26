package com.lufecrx.sistema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lufecrx.sistema.entidades.Reembolso;

public class ReembolsoDAO {

    public static void criar(Reembolso reembolso, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(reembolso);
        entityManager.getTransaction().commit();
    }

    public static List<Reembolso> retornarTodos(EntityManager entityManager) {
        String jpql = "Select r FROM Reembolso r";

        TypedQuery<Reembolso> query = entityManager.createQuery(jpql, Reembolso.class);
        List<Reembolso> reembolsos = query.getResultList();

        return reembolsos;
    }

    public static Reembolso retornarPelaID(String id, EntityManager entityManager) {
        String jpql = "Select r FROM Reembolso r WHERE r.idFatura = :id";

        TypedQuery<Reembolso> query = entityManager.createQuery(jpql, Reembolso.class);
        query.setParameter("id", id);

        Reembolso reembolso = query.getSingleResult();

        return reembolso;                
    }

    public static void atualizar(Reembolso reembolso, EntityManager entityManager) {
        String jpql = "SELECT r FROM Reembolso r WHERE r.idFatura = :id";

        TypedQuery<Reembolso> query = entityManager.createQuery(jpql, Reembolso.class);
        query.setParameter("id", reembolso.getFatura().getIdFatura());
        
        Reembolso reembolsoAtualizado = query.getSingleResult();

        reembolsoAtualizado.setValor(reembolso.getValor());
        reembolsoAtualizado.setData(reembolso.getData());

        entityManager.getTransaction().begin();
        entityManager.persist(reembolsoAtualizado);
        entityManager.getTransaction().commit();
    }

    public static void deletar(String idFatura, EntityManager entityManager) {
        String jpql = "SELECT r FROM Reembolso r WHERE r.idFatura = :id";

        TypedQuery<Reembolso> query = entityManager.createQuery(jpql, Reembolso.class);
        query.setParameter("id", idFatura);

        Reembolso reembolso = query.getSingleResult();

        entityManager.getTransaction().begin();
        entityManager.remove(reembolso);
        entityManager.getTransaction().commit();
    }

}