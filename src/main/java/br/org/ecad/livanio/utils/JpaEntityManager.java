package br.org.ecad.livanio.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManager {
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("texto_twitter");
	private EntityManager em = factory.createEntityManager();
	
	public EntityManager getEntityManager() {
		return em;
	}
}
