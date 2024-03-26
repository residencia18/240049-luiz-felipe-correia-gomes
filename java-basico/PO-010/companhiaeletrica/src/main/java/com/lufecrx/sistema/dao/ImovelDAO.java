package com.lufecrx.sistema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lufecrx.sistema.entidades.Imovel;

public class ImovelDAO {

    public static void criar(Imovel imovel, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(imovel);
        entityManager.getTransaction().commit();
    }

    public static List<Imovel> retornarTodos(EntityManager entityManager) {
        String jpql = "Select i FROM Imovel i";

        TypedQuery<Imovel> query = entityManager.createQuery(jpql, Imovel.class);
        List<Imovel> imoveis = query.getResultList();

        return imoveis;
    }

    public static Imovel retornarPelaMatricula(int matricula, EntityManager entityManager) {
        String jpql = "Select i FROM Imovel i WHERE i.matricula = :matricula";

        TypedQuery<Imovel> query = entityManager.createQuery(jpql, Imovel.class);
        query.setParameter("matricula", matricula);
        Imovel imovel = query.getSingleResult();

        return imovel;        
    }

    public static void atualizar(Imovel imovel, EntityManager entityManager) {
        String jpql = "SELECT i FROM Imovel i WHERE i.matricula = :matricula";

        TypedQuery<Imovel> query = entityManager.createQuery(jpql, Imovel.class);
        query.setParameter("matricula", imovel.getMatricula());

        Imovel imovelAtualizado = query.getSingleResult();
        imovelAtualizado.setEndereco(imovel.getEndereco());

        entityManager.getTransaction().begin();
        entityManager.persist(imovelAtualizado);
        entityManager.getTransaction().commit();
    }

    public static void deletar(int matricula, EntityManager entityManager) {
        String jpql = "SELECT i FROM Imovel i WHERE i.matricula = :matricula";

        TypedQuery<Imovel> query = entityManager.createQuery(jpql, Imovel.class);
        query.setParameter("matricula", matricula);

        Imovel imovel = query.getSingleResult();

        entityManager.getTransaction().begin();
        entityManager.remove(imovel);
        entityManager.getTransaction().commit();
    }

}