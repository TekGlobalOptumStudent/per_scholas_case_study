package com.PatchworkNovels.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO {
	
	private final String PERSISTENCE_UNIT_NAME = "PatchworkNovels";
	
	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	
	public void connect() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}
	
	public void dispose() {
		if(em != null && em.isOpen()) em.close();
		if(emf != null && emf.isOpen()) emf.close();
	}
}
