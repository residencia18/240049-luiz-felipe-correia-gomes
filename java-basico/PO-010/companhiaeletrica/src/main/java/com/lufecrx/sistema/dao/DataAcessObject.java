package com.lufecrx.sistema.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataAcessObject {

	public static EntityManager criarGerenciadorDeEntidade() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_companhiaeletrica");	
		EntityManager entityManager = emf.createEntityManager();

		return entityManager;
	}
}