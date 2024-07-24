package com.azienda.social.model;



import java.sql.Blob;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String testo;
	private LocalDateTime data_post;
	private Blob immagine;
	private String nomeImmagine;
	@OneToMany(mappedBy = "post")
	private List<Commenti> commenti=new ArrayList<Commenti>();
	@ManyToOne
	private Utente utente;
	@ManyToMany(mappedBy = "postLike")
	List<Utente> listaLikers=new ArrayList<Utente>();
	@ManyToMany(mappedBy = "postDislike")
	List<Utente> listaDislikers=new ArrayList<Utente>();

	
	
	public Post() {
		super();
	}

	public Post(String testo, LocalDateTime data_post) {
		super();
		this.testo = testo;
		this.data_post = data_post;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDateTime getData_post() {
		return data_post;
	}

	public void setData_post(LocalDateTime data_post) {
		this.data_post = data_post;
	}

	public List<Commenti> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commenti> commenti) {
		this.commenti = commenti;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Utente> getListaLikers() {
		return listaLikers;
	}

	public void setListaLikers(List<Utente> listaLikers) {
		this.listaLikers = listaLikers;
	}

	public List<Utente> getListaDislikers() {
		return listaDislikers;
	}

	public void setListaDislikers(List<Utente> listaDislikers) {
		this.listaDislikers = listaDislikers;
	}

	
	
	public Blob getImmagine() {
		return immagine;
	}

	public void setImmagine(Blob immagine) {
		this.immagine = immagine;
	}

	public String getNomeImmagine() {
		return nomeImmagine;
	}

	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}

	@Override
	public String toString() {
		return "Post [testo=" + testo + ", data_post=" + data_post + "]";
	}
	
	
}