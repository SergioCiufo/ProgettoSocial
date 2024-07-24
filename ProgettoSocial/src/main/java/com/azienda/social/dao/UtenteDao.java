package com.azienda.social.dao;

import java.sql.Blob;
import java.util.List;
import javax.persistence.EntityManager;

import com.azienda.social.model.Post;
import com.azienda.social.model.Utente;


public class UtenteDao implements DaoInterface<Utente>{
	EntityManager manager;
	
	
	public UtenteDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	public UtenteDao() {
		super();
	}

	@Override
	public Utente create(Utente ref) {
		manager.persist(ref);
		return ref;
	}

	@Override
	public List<Utente> retrieve() {
		return manager.createQuery("select x from Utente x",Utente.class).getResultList();
	}

	@Override
	public Utente update(Utente ref) {
		manager.persist(ref);
		return ref;
	}

	@Override
	public void delete(Utente ref) {
		manager.remove(ref);
		
	}
	public List<Utente> findbyName(String nome,String cognome) {
		return  manager.createQuery("select x from Utente x where (x.nome like :parName or x.cognome like :parName) and (x.nome like :parCogn or x.cognome like :parCogn)",Utente.class).setParameter("parName","%"+nome+"%").setParameter("parCogn","%"+cognome+"%").getResultList();
	}
	
	public List<Utente> findbyName(String nome) {
		return  manager.createQuery("select x from Utente x where x.nome like :parName or x.cognome like :parName",Utente.class).setParameter("parName", "%"+nome+"%").getResultList();
	}
	
	
	public List<Utente> findbyUsername(String user) {
		return  manager.createQuery("select x from Utente x where x.username=:parUser",Utente.class).setParameter("parUser", user).getResultList();
	}
	
	
	public List<Utente> findbyEmail(String email) {
		return  manager.createQuery("select x from Utente x where x.email=:parEmail",Utente.class).setParameter("parEmail", email).getResultList();
	}
	
	public Utente findbyId(Integer id) {
		return  manager.createQuery("select x from Utente x where x.id=:parID",Utente.class).setParameter("parID", id).getSingleResult();
	}
	
    public List<Utente> findByPostLike(Post post) {
        return manager.createQuery("SELECT u FROM Utente u JOIN u.postLike p WHERE p = :post", Utente.class).setParameter("post", post).getResultList();
    }
    
    public List<Utente> findByPostDislike(Post post) {
    	return manager.createQuery("SELECT u FROM Utente u JOIN u.postDislike p WHERE p = :post", Utente.class).setParameter("post", post).getResultList();
    }

}