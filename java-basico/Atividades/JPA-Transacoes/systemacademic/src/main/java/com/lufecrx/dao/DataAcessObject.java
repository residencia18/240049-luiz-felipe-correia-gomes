package com.lufecrx.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lufecrx.academico.Curso;
import com.lufecrx.academico.Estudante;
import com.lufecrx.dto.EstudanteDTO;

public class DataAcessObject {
    
    public static void prepararBanco(EntityManager em) {
        System.out.println("Preparando o banco...");

        Curso c1 = new Curso("Informática", 4);
        Curso c2 = new Curso("Ciência da Computação", 4);
        Curso c3 = new Curso("Engenharia de Computação", 4);

        Estudante e1 = new Estudante(c1 ,"Lucas", "lucas@lucas", "123");
        Estudante e2 = new Estudante(c1 ,"Fernando", "fernando@fernando", "456");
        Estudante e3 = new Estudante(c2, "Gabriel", "gabriel@gabriel", "789");
        Estudante e4 = new Estudante(c2, "Pedro", "pedro@pedro", "321");
        Estudante e5 = new Estudante(c3, "Bruno", "bruno@bruno", "654");
        Estudante e6 = new Estudante(c3, "Carlos", "carlos@carlos", "987");        

        em.getTransaction().begin();
        
        em.persist(c3);
        em.persist(c2);
        em.persist(c1);
        
        em.persist(e6);
        em.persist(e5);
        em.persist(e4);
        em.persist(e3);
        em.persist(e2);
        em.persist(e1);
        em.getTransaction().commit();
        
        System.out.println("Banco preparado!");
    }

    public static List<Estudante> retornarEstudantes(EntityManager em) {
        String jpql = "select e from Estudante e";

        System.out.println("Carregando estudantes...");
        TypedQuery<Estudante> query = em.createQuery(jpql, Estudante.class);
        List<Estudante> estudantes = query.getResultList();

        return estudantes;
    }
    
    public static Estudante retornarEstudante (EntityManager em, int id) {
        String jpql = "select e from Estudante e where e.id = :id";
        
        TypedQuery<Estudante> query = em.createQuery(jpql, Estudante.class);
        query.setParameter("id", id);
        
        return query.getSingleResult();
    }
    
    public static void alterarEstudante (EntityManager em, int id) {
        String jpql = "select e from Estudante e where e.id = :id";
        
        TypedQuery<Estudante> query = em.createQuery(jpql, Estudante.class);
        query.setParameter("id", id);

        Estudante estudante = query.getSingleResult();
        System.out.println(estudante);
        em.getTransaction().begin();
        estudante.setEmail("crazyguy@lufecrx");
        em.persist(estudante);
        em.getTransaction().commit();
    
        System.out.println(estudante);
    }

    public static void listarNomeDosEstudantes (EntityManager em) {
        String jpql = "select e.nome from Estudante e";
        TypedQuery<String> query = em.createQuery(jpql, String.class);

        List<String> nomes = query.getResultList();
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }

    public static void gerarEstudanteDTO (EntityManager em) {
        String jpql = "select new com.lufecrx.dto.EstudanteDTO(e.nome, e.matricula) from Estudante e";
        TypedQuery<EstudanteDTO> query = em.createQuery(jpql, EstudanteDTO.class);
        
        List<EstudanteDTO> estudantes = query.getResultList();
        for (EstudanteDTO estudante : estudantes) {
            System.out.println(estudante);
        }
    }
}
    