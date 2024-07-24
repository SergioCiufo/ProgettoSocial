package com.azienda.social.dao;

import java.util.List;
import javax.persistence.EntityManager;
import com.azienda.social.model.Post;
import com.azienda.social.model.Utente;

public class PostDao implements DaoInterface<Post>{
	EntityManager manager;

	public PostDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Post create(Post ref) {
		manager.persist(ref);
		return ref;
	}

	@Override
	public List<Post> retrieve() {
		return manager.createQuery("select x from Post x",Post.class).getResultList();
	}

	@Override
	public Post update(Post ref) {
		manager.persist(ref);
		return ref;
	}

	@Override
	public void delete(Post ref) {
		manager.remove(ref);
		
	}
	
	public Post findById(Integer id){
		return manager.createQuery("select x from Post x where x.id=:parId",Post.class).setParameter("parId", id).getSingleResult();
	}
	
	public List<Post> allPost(){
		return manager.createQuery("select x from Post x order by data_post",Post.class).getResultList();
	}
	//select per andarci a prendere ed ordinare in modo decrescente i post che hanno più like
	public List<Post> ordinaByLike(){
		return manager.createQuery( "SELECT x FROM Post x ORDER BY SIZE(x.listaLikers) DESC", Post.class).getResultList();
	}
	
	public List<Post> ordinaByPiùRecente(){
		return manager.createQuery("SELECT x FROM Post x ORDER BY data_post DESC", Post.class).getResultList();
	}
	
}
