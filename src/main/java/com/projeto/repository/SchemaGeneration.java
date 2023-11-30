package com.projeto.repository;

import java.util.List;

import com.projeto.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SchemaGeneration {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplicacaoPU");
		
		EntityManager em = emf.createEntityManager();
		
		List<Usuario> lista = em.createQuery("From Usuario", Usuario.class).getResultList();
		
		System.out.println(lista);
		
		em.close();
		emf.close();
		
	}
	
}
