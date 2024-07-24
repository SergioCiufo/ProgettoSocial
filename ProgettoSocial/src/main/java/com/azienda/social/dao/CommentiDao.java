package com.azienda.social.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.social.model.Commenti;
import com.azienda.social.model.Post;


public class CommentiDao implements DaoInterface<Commenti>{
	EntityManager manager;
	
	
	public CommentiDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Commenti create(Commenti ref) {
		manager.persist(ref);
		return ref;
	}

	@Override
	public List<Commenti> retrieve() {
		return manager.createQuery("select x from Commenti x",Commenti.class).getResultList();
	}

	@Override
	public Commenti update(Commenti ref) {
		manager.persist(ref);
		return ref;
	}

	@Override
	public void delete(Commenti ref) {
		manager.remove(ref);
		
	}
	
	public List<Commenti> findbyPostId(Post post){
		return manager.createQuery("select x from Commenti x where x.post=:parId",Commenti.class).setParameter("parId", post).getResultList();
	}

}

