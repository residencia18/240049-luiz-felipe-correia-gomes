package com.lufecrx.academico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.lufecrx.dao.DataAcessObject;

public class Teste {

    public static void criar(EntityManager entityManager) {
        Curso c1 = new Curso("Informática", 4);
        Curso c2 = new Curso("Ciência da Computação", 4);
        Curso c3 = new Curso("Engenharia de Computação", 4);

        Estudante e1 = new Estudante(c1, "Lucas", "lucas@lucas", "123");
        Estudante e2 = new Estudante(c2, "Fernando", "fernando@fernando", "456");
        Estudante e3 = new Estudante(c3, "Gabriel", "gabriel@gabriel", "789");

        entityManager.getTransaction().begin();
        entityManager.persist(e1);
        entityManager.persist(e2);
        entityManager.persist(e3);
        entityManager.getTransaction().commit();
    }

    public static void find(EntityManager entityManager) {
        Estudante e = entityManager.find(Estudante.class, 1);
        System.out.println(e);
    }

    public static void prepararBD(EntityManager entityManager) {
        DataAcessObject.prepararBanco(entityManager);
    }

    public static void listarEstudantes(EntityManager entityManager) {
        List<Estudante> estudantes = DataAcessObject.retornarEstudantes(entityManager);
        
        if (estudantes.isEmpty()) {
            System.out.println("Nenhum estudante encontrado");
        }

        for (Estudante e : estudantes) {
            System.out.println(e);
        }
    }

    public static void getEstudantePeloId(EntityManager entityManager) {
        int id = 2;
        Estudante e = DataAcessObject.retornarEstudante(entityManager, id);
        System.out.println(e);
    }

    public static void alterarEstudante(EntityManager entityManager) {
        int id = 3;
        DataAcessObject.alterarEstudante(entityManager, id);
    }

    public static void listarNomeDosEstudantes (EntityManager entityManager) {
        DataAcessObject.listarNomeDosEstudantes(entityManager);
    }

    public static void gerarEstudanteDTO (EntityManager entityManager) {
        DataAcessObject.gerarEstudanteDTO(entityManager);
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");
        EntityManager em = emf.createEntityManager();

        // criar(em);
        // find(em);
        prepararBD(em);
        // listarEstudantes(em);
        // getEstudantePeloId(em);
        // alterarEstudante(em);
        // listarNomeDosEstudantes(em);
        gerarEstudanteDTO(em);

        em.close();
        emf.close();        
    }
}
