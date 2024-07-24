package com.azienda.social.utils;
//LE INTERFACCE SONO SEMPRE PUBLIC FINAL STATIC
public interface Costanti {
	
	public final static String PERSISTENCE="ProgettoSocial";
	
	public final static String CHIAVE_SERVICE = "service";
	public final static String CHIAVE_SESSIONE_UTENTE= "chiaveSessione";
	public final static String CHIAVE_PAGINA_ATTUALE="PaginaAttuale";
	
	public final static String CHIAVE_ERRORE_LOGIN="erroreLogin";
	public final static String CHIAVE_ERRORE_REGISTRAZIONE_USERNAME="ErroreUsername";
	public final static String CHIAVE_ERRORE_REGISTRAZIONE_EMAIL="ErroreEmail";
	public final static String CHIAVE_ERRORE_COMMENTO="erroreCommento";
	
	public final static String CHIAVE_ELENCO_POST="elencPost";
	public final static String CHIAVE_ELENCO_POST_PERSONALI="postPersonali";
	public final static String CHIAVE_ELENCO_COMMENTI="elencoCommenti";
	public final static String CHIAVE_UTENTE_RICERCATO="utenteRicercato";
	public final static String CHIAVE_UTENTI_TROVATI="utentiRicerca";
	
	public final static String VALORE_PAGINA_ATTUALE_HOME="Home";
	public final static String VALORE_PAGINA_ATTUALE_PROFILO="Profilo";
	public final static String VALORE_PAGINA_ATTUALE_PROFILOGENERICO="ProfiloGenerico";
	
	public final static String HTML_FORM_REGISTRAZIONE_USER="unR";
	public final static String HTML_FORM_REGISTRAZIONE_PASSWORD="pswR";
	public final static String HTML_FORM_REGISTRAZIONE_EMAIL="emailR";
	public final static String HTML_FORM_REGISTRAZIONE_DDN="ddnR";
	public final static String HTML_FORM_REGISTRAZIONE_NOME="nomeR";
	public final static String HTML_FORM_REGISTRAZIONE_COGNOME="cognomeR";
	
	public final static String HTML_FORM_LOGIN_USER_OR_EMAIL= "un";
	public final static String HTML_FORM_LOGIN_PASSWORD= "psw";
	
	public final static String HTML_FORM_MODIFICA_USER="userMod";
	public final static String HTML_FORM_MODIFICA_PASSWORD="pswMod";
	public final static String HTML_FORM_MODIFICA_EMAIL="emailMod";
	public final static String HTML_FORM_MODIFICA_DDN="ddnMod";
	public final static String HTML_FORM_MODIFICA_NOME="nomeMod";
	public final static String HTML_FORM_MODIFICA_COGNOME="cogMod";
	
	public final static String HTML_FORM_POST="post";
	
	public final static String HTML_FORM_LIKE_NAME="action";
	public final static String HTML_FORM_LIKE_VALUE="toggleLike";
	public final static String HTML_FORM_LIKE_POSTID="postId";
	
	public final static String HTML_FORM_DISLIKE_NAME="actionD";
	public final static String HTML_FORM_DISLIKE_VALUE="toggleDislike";
	public final static String HTML_FORM_DISLIKE_POSTID="postIdD";
	
	public final static String HTML_FORM_COMMENTA="commentoPost";
	public final static String HTML_FORM_COMMENTA_ID_POST="postIdC";
	
	public final static String HTML_FORM_ELIMINA_POST="postIdElimina";
	
	public final static String HTML_FORM_SELECT_ORDINAPOST="filtroPost";
	public final static String HTML_FORM_SELECT_ORDINAPOST_PIULIKE="piuLike";
	public final static String HTML_FORM_SELECT_ORDINAPOST_PIURECENTE="piuRecente";
	public final static String HTML_FORM_SELECT_ORDINAPOST_MENORECENTE="menoRecente";
	
	public final static String HTML_FORM_RICERCA="ricerca";
	public final static String HTML_FORM_ELENCO_PROFILO="RicercaUtenteId";
	
}
