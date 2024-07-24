package com.azienda.social.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
@Entity
//@Table()
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true,nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@Column(unique = true,nullable = false)
	private String email;
	private String ddn;
	private Blob immagine;
	private String nomeImmagine;
	//immagine profilo
	
	@OneToMany(mappedBy = "utente")
	private List<Commenti> commenti=new ArrayList<Commenti>();
	
	@OneToMany(mappedBy = "utente")
	private List<Post> postPersonali=new ArrayList<Post>();
	
	@ManyToMany
	@JoinTable(name = "Like_post",uniqueConstraints = {@UniqueConstraint(columnNames = {"Utenti_id","Post_id"} )},
	joinColumns = @JoinColumn(name="Utenti_id"),
	inverseJoinColumns = @JoinColumn(name="Post_id"))
	private List<Post> postLike=new ArrayList<Post>();
	
	@ManyToMany
	@JoinTable(name ="Dislike_post",uniqueConstraints = {@UniqueConstraint(columnNames = {"Utenti_id","Post_id"} )},
	joinColumns = @JoinColumn(name="Utenti_id"),
	inverseJoinColumns = @JoinColumn(name="Post_id"))
	private List<Post> postDislike=new ArrayList<Post>();
	
	
	
	public Utente() {
		super();
	}

	
	public Utente(String username, String password, String nome, String cognome, String email) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}


	public Utente(String username, String password, String nome, String cognome, String email, String ddn) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.ddn = ddn;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		Utente other = (Utente) obj;
		return Objects.equals(id, other.id);
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDdn() {
		return ddn;
	}

	public void setDdn(String ddn) {
		this.ddn = ddn;
	}

	public List<Commenti> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commenti> commenti) {
		this.commenti = commenti;
	}

	

	public List<Post> getPostPersonali() {
		return postPersonali;
	}

	public void setPostPersonali(List<Post> postPersonali) {
		this.postPersonali = postPersonali;
	}

	public List<Post> getPostLike() {
		return postLike;
	}

	public void setPostLike(List<Post> postLike) {
		this.postLike = postLike;
	}

	public List<Post> getPostDislike() {
		return postDislike;
	}

	public void setPostDislike(List<Post> postDislike) {
		this.postDislike = postDislike;
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
		return "Utenti [username=" + username + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", ddn=" + ddn + "]";
	}
	
	
}