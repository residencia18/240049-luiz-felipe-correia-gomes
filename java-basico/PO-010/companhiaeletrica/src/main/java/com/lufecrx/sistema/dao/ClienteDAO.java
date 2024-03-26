package com.lufecrx.sistema.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lufecrx.sistema.entidades.Cliente;

public class ClienteDAO {

    public static void criar(Cliente cliente, EntityManager entityManager) {

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }

    public static List<Cliente> retornarTodos(EntityManager entityManager) {
        String jpql = "Select c FROM Cliente c";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> clientes = query.getResultList();

        return clientes;
    }

    public static Cliente retornarPeloCPF(String cpf, EntityManager entityManager) {
        String jpql = "Select c FROM Cliente c WHERE c.cpf = :cpf";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("cpf", cpf);

        return query.getSingleResult();
    }

    public static void atualizar(Cliente cliente, EntityManager entityManager) {
        String jpql = "SELECT c FROM Cliente c WHERE c.cpf = :cpf";

        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("cpf", cliente.getCpf());

        Cliente clienteAtualizado = query.getSingleResult();
        clienteAtualizado.setNome(cliente.getNome());
        clienteAtualizado.setPropriedade(cliente.getPropriedade());

        entityManager.getTransaction().begin();
        entityManager.persist(clienteAtualizado);
        entityManager.getTransaction().commit();
    }

    public static void deletar(String cpf, EntityManager entityManager) {
        String jpql = "SELECT c FROM Cliente c WHERE c.cpf = :cpf";
        
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("cpf", cpf);

        Cliente cliente = query.getSingleResult();
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
    }

}