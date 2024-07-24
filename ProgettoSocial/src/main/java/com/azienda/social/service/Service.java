package com.azienda.social.service;

import java.sql.Blob;


import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;

import com.azienda.social.dao.CommentiDao;
import com.azienda.social.dao.PostDao;
import com.azienda.social.dao.UtenteDao;
import com.azienda.social.exception.CommentoNonValidoException;
import com.azienda.social.exception.CredenzialiNonValideException;
import com.azienda.social.exception.RegistrazioneEmailEsistenteException;
import com.azienda.social.exception.RegistrazioneUsernameEsistenteException;
import com.azienda.social.model.Commenti;
import com.azienda.social.model.Post;
import com.azienda.social.model.Utente;

public class Service {
	private EntityManager manager;
	private UtenteDao utenteDao;
	private CommentiDao commentiDao;
	private PostDao postDao;

	public Service() {
	}
	public Service(EntityManager manager, UtenteDao utenteDao, CommentiDao commentiDao, PostDao postDao) {
		this.manager = manager;
		this.utenteDao = utenteDao;
		this.commentiDao = commentiDao;
		this.postDao = postDao;
	}
	
	//registrazione con data
	public String registrazioneUtente(String username,String password,String email,String ddn,String nome,String cognome) throws Exception {
		String controllo=null;
		try {
			manager.getTransaction().begin();
			Utente user=new Utente(username, password, nome, cognome, email, ddn);
			//se non è vuoto (!) //quindi se l'username è già esistente
			if(!utenteDao.findbyUsername(username).isEmpty()) {
				throw new RegistrazioneUsernameEsistenteException("Username già esistente" , null);
				//controllo="Username già esistente ";
				//manager.getTransaction().rollback();
			//se non è vuoto (!) //quindi se l'email è già esistente
			}else if(!utenteDao.findbyEmail(email).isEmpty()) {
				throw new RegistrazioneEmailEsistenteException("Email già esistente", null);
				//controllo="Email già esistente ";
				//manager.getTransaction().rollback();
			//se non esiste allora crea l'utente
			}else {
				manager.persist(user);	
				utenteDao.create(user);
				manager.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}return controllo;
	}
	/*
	//registrazione senza data

	public String registrazioneUtente(String username,String password,String email,String nome,String cognome) {
		String controllo=null;
		try {
			manager.getTransaction().begin();
			Utente user=new Utente(username, password, nome, cognome, email);
			//se non è vuoto (!) //quindi se l'username è già esistente
			if(!utenteDao.findbyUsername(username).isEmpty()) {
				controllo="Username già esistente ";
				manager.getTransaction().rollback();
			}
			//se non è vuoto (!) //quindi se l'email è già esistente
			else if(!utenteDao.findbyEmail(email).isEmpty()) {
				controllo="Email già esistente ";
				manager.getTransaction().rollback();
			}
			//se non esiste allora crea l'utente
			else {
				manager.persist(user);
				utenteDao.create(user);
				manager.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}return controllo;
	}
	*/
	
	public void creaPost(String testo,Utente utente,Blob immagine,String nomeImmagine) {
		try {
			manager.getTransaction().begin();
			//crea la data/orario NOW
			LocalDateTime ldt=LocalDateTime.now();
			Post post=new Post(testo, ldt);
			post.setUtente(utente);
			//se l'utente ha messo l'immagine
			if(immagine!=null) {
				post.setImmagine(immagine);
				post.setNomeImmagine(nomeImmagine);
			}
			postDao.create(post);
			List<Post> postpersonali=utente.getPostPersonali();
			postpersonali.add(post);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}
	}

	public Utente controlloLogin(String user,String password) throws Exception {
		Utente utente=null;
		try {
			manager.getTransaction().begin();
			List<Utente> utenti=utenteDao.retrieve();
			for(Utente u:utenti) {
				if((u.getUsername().equals(user) && u.getPassword().equals(password)) || (u.getEmail().equals(user) && u.getPassword().equals(password))) {
					utente=u;
					break;
				}
			}
			//se l'utente non inserisce delle credenziali valide
			if (utente==null) {
				throw new CredenzialiNonValideException("Credenziali non valide" , null);
			}
			manager.getTransaction().commit();
			return utente;
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}		
	}

	public List<Post> tuttiPost(){
		List<Post> elencoPost=new ArrayList<Post>();
		try {
			manager.getTransaction().begin();
			elencoPost=postDao.allPost();
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}
		return elencoPost;	
	}


	public List<Post> postPersonali(Utente u){
		List<Post> postPersonali;
		try {
			manager.getTransaction().begin();
			postPersonali=	u.getPostPersonali();
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}return postPersonali;
	}

	public void aggiungiLike(Utente u,Integer id)  {

		try {

			manager.getTransaction().begin();
			Post post=postDao.findById(id);	
			List<Utente> listaLikers=post.getListaLikers();
			List<Utente> listaDislikers=post.getListaDislikers();	
			if(listaLikers.contains(u)) {
				listaLikers.remove(u);
				u.getPostLike().remove(post);
				manager.persist(post);
				manager.persist(u);
			}
			else {
				listaLikers.add(u); 
				u.getPostLike().add(post);
				if(listaDislikers.contains(u)) {
					listaDislikers.remove(u);
					u.getPostDislike().remove(post);		
				}
				manager.persist(post);
				manager.persist(u);
			}			
			manager.getTransaction().commit();
		}  
		catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;

		}
	}
	public void aggiungiDislike(Utente u,Integer id) {

		try {
			manager.getTransaction().begin();
			Post post=postDao.findById(id);	
			List<Utente> listaDislikers=post.getListaDislikers();
			List<Utente> listaLikers=post.getListaLikers();

			if(listaDislikers.contains(u)) {
				listaDislikers.remove(u);
				u.getPostDislike().remove(post);
				manager.persist(post);
				manager.persist(u);
			}
			else {
				listaDislikers.add(u); 
				u.getPostDislike().add(post);
				if(listaLikers.contains(u)) {
					listaLikers.remove(u);
					u.getPostLike().remove(post);

				}
				manager.persist(post);
				manager.persist(u);
			}
			manager.getTransaction().commit();
		}  
		catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;				
		}
	}

	public void aggiungiCommento(Utente u, String testo, Integer idPost) throws Exception {
	    try {
	        manager.getTransaction().begin();
	        
	        if (testo.isBlank()) {
	        	throw new CommentoNonValidoException ("Commento non valido", null);
			}
	        
	        // Ottiene la lista dei commenti dell'utente
	        List<Commenti> elencoCommenti = u.getCommenti();	        
	        // Ottiene l'orario corrente
	        LocalDateTime ldt = LocalDateTime.now();        
	        // Crea un nuovo oggetto Commenti con il testo fornito e l'orario corrente
	        Commenti commento = new Commenti(testo, ldt);   
	        // Trova il post con l'ID fornito
	        Post post = postDao.findById(idPost);
	        // Ottiene la lista dei commenti associati al post
	        List<Commenti> elencoCommentiPost = post.getCommenti();
	        // Imposta l'utente del commento
	        commento.setUtente(u);
	        // Imposta il post del commento
	        commento.setPost(post);
	        // Aggiunge il commento alla lista dei commenti dell'utente
	        elencoCommenti.add(commento);
	        // Aggiunge il commento alla lista dei commenti del post
	        elencoCommentiPost.add(commento);
	        // Stampa il commento
	        System.out.println(commento);

	        // Salva il commento nel database
	        commentiDao.create(commento);
	        manager.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}


	public List<Commenti> commentiPost(Integer id) {
	    // Inizializza la lista dei commenti come null
	    List<Commenti> commenti = null;
	    try {
	        manager.getTransaction().begin();
	        // Trova il post con l'ID fornito
	        Post post = postDao.findById(id);
	        // Ottiene la lista dei commenti associati al post
	        commenti = post.getCommenti();
	        manager.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        manager.getTransaction().rollback();
	        throw e;
	    }
	    return commenti;
	}


	public List<Commenti> tuttiCommenti(){
		List<Commenti> commenti=null;
		try {
			manager.getTransaction().begin();
			commenti=commentiDao.retrieve();
			manager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}return commenti;
	}

	public List<Utente> tuttiUtenti(){
		List<Utente> utenti=null;
		try {
			manager.getTransaction().begin();
			utenti=utenteDao.retrieve();
			manager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}return utenti;
	}

	public List<Utente> findbyName(String ricerca){
		List<Utente> utenti=null;
		try {
			manager.getTransaction().begin();

			String[] valori= ricerca.split(" ");
			if(valori.length==1) {
				String valore1=valori[0];
				utenti=utenteDao.findbyName(valore1);
			}
			else if( valori.length==2) {
				String valore1=valori[0];
				String valore2=valori[1]; 
				utenti=utenteDao.findbyName(valore1,valore2);
			}

			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}
		return utenti;
	} 


	public void modificaProfilo(Utente u,Utente utenteAttivo) {
		try {
			manager.getTransaction().begin();
			if(!u.getNome().isBlank() && u.getNome()!=null) {
				utenteAttivo.setNome(u.getNome());
			}
			if(!u.getCognome().isBlank() && u.getCognome()!=null) {
				utenteAttivo.setCognome(u.getCognome());
			}
			if(!u.getEmail().isBlank() && u.getEmail()!=null) {
				utenteAttivo.setEmail(u.getEmail());
			}
			if(!u.getPassword().isBlank() && u.getPassword()!=null) {
				utenteAttivo.setPassword(u.getPassword());
			}
			if(!u.getDdn().isBlank() && u.getDdn()!=null) {
				utenteAttivo.setDdn(u.getDdn());
			}
			if(!u.getUsername().isBlank() && u.getUsername()!=null) {
				utenteAttivo.setUsername(u.getUsername());
			}

			if(u.getImmagine()!=null) {
				utenteAttivo.setImmagine(u.getImmagine());
			}
			if(u.getNomeImmagine()!=null) {
				utenteAttivo.setNomeImmagine(u.getNomeImmagine());
			}
			Utente utenteModificato=utenteDao.update(utenteAttivo);
			manager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}
	}
	/* OLD SI BUGGAVA
	public void eliminaPost(Integer id,Utente u) {
		try {
			manager.getTransaction().begin();
			Post post=postDao.findById(id);
			System.out.println("Stampa del post interessato "+post);
			
	        // Verifica e debug delle collezioni
	        System.out.println("PostLike before removal: " + u.getPostLike().contains(post));
	        System.out.println("PostDislike before removal: " + u.getPostDislike().contains(post));
	        System.out.println("PostPersonali before removal: " + u.getPostPersonali().contains(post));
	        
	        u.getPostLike().remove(post);
	        u.getPostDislike().remove(post);
	        u.getPostPersonali().remove(post);
	        

	        // Verifica delle modifiche alle collezioni
	        System.out.println("PostLike after removal: " + u.getPostLike());
	        System.out.println("PostDislike after removal: " + u.getPostDislike());
	        System.out.println("PostPersonali after removal: " + u.getPostPersonali());

			List<Commenti> commentiPost=commentiDao.findbyPostId(post);
			for (Commenti c:commentiPost) {
				commentiDao.delete(c);
			}
			postDao.delete(post);
			manager.getTransaction().commit();
		} 
		catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}	
	}
	*/	
	
	public void eliminaPost(Integer id, Utente u) {
	    try {
	        manager.getTransaction().begin();

	        // Trova il post da eliminare
	        Post post = postDao.findById(id);

	        // Rimuovi il post dai like di tutti gli utenti
	        List<Utente> utentiConLike = utenteDao.findByPostLike(post);
	        for (Utente utente : utentiConLike) {
	            utente.getPostLike().remove(post);
	        }

	        // Rimuovi il post dai dislike di tutti gli utenti
	        List<Utente> utentiConDislike = utenteDao.findByPostDislike(post);
	        for (Utente utente : utentiConDislike) {
	            utente.getPostDislike().remove(post);
	        }

	        // Rimuovi il post dalla lista dei post personali dell'utente
	        u.getPostPersonali().remove(post);

	        // Rimuovi tutti i commenti associati al post
	        List<Commenti> commentiPost = commentiDao.findbyPostId(post);
	        for (Commenti c : commentiPost) {
	            commentiDao.delete(c);
	        }

	        // Elimina il post
	        postDao.delete(post);

	        manager.getTransaction().commit();
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	        manager.getTransaction().rollback();
	        throw e;
	    }
	}


	public Post TrovaPost(Integer id) {
		Post post;
		try {
			manager.getTransaction().begin();
			post=postDao.findById(id);	
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}return post;
	}

	public Utente findUtentebyId(Integer id) {
		Utente utente;
		try {
			manager.getTransaction().begin();
			utente=utenteDao.findbyId(id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}return utente;
	}
	//metodo ordinaLike
	public List<Post> ordinaByLike() {
		//ci creiamo un arraylist di post
		List<Post> post = new ArrayList<Post>();
		//post lo dichiariamo null per ogni chiamata
		post=null;
		try {
			manager.getTransaction().begin();
			System.out.println("INIZIO LETTURA POST CON PIU LIKE");
			//chiamata al metodo che sta nel postDao
			post=postDao.ordinaByLike();
			for (Post p: post) {
				System.out.println(p);
			}
			System.out.println("FINE LETTURA POST CON PIU LIKE");
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}
		return post;
	}
	
	public List<Post> ordinaByPiùRecente(){
		List<Post> post = new ArrayList<Post>();
		post=null;
		try {
			manager.getTransaction().begin();
			System.out.println("INIZIO LETTURA POST PIU RECENTI");
			post=postDao.ordinaByPiùRecente();
			for(Post p : post) {
				System.out.println(p);
			}
			System.out.println("FINE LETTURA POST PIU RECENTI");
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			throw e;
		}
		return post;
	}
	
}
