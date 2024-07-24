package com.azienda.social.ui;

import java.io.IOException;
import java.util.List;

import com.azienda.social.exception.CommentoNonValidoException;
import com.azienda.social.exception.CredenzialiNonValideException;
import com.azienda.social.model.Commenti;
import com.azienda.social.model.Post;
import com.azienda.social.model.Utente;
import com.azienda.social.service.Service;
import com.azienda.social.utils.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/commento")
public class Commenta extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//Service service=(Service)getServletContext().getAttribute("service");
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			//String commentoPost=req.getParameter("commentoPost");
			String commentoPost=req.getParameter(Costanti.HTML_FORM_COMMENTA);
			//Utente utente=(Utente)req.getSession().getAttribute("chiaveSessione");
			Utente utente=(Utente)req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			//String post=req.getParameter("postIdC");
			String post=req.getParameter(Costanti.HTML_FORM_COMMENTA_ID_POST);
			Integer idPost=Integer.parseInt(post);
			//ci prendiamo la pagina attuale per reindirizzare l'utente dove effettivamente ha scritto (righe in basso)
			//String pagina=(String)req.getSession().getAttribute("PaginaAttuale");
			String pagina=(String)req.getSession().getAttribute(Costanti.CHIAVE_PAGINA_ATTUALE);
/*
			if(commentoPost.isBlank()) {	
				req.setAttribute("erroreCommento", "commento non valido"); 
			}
			else 
			{
*/
				service.aggiungiCommento(utente,commentoPost,idPost);
				//req.setAttribute("erroreCommento", "");
/*

			}
*/
			//if(pagina.equals("Profilo")) {
			if(pagina.equals(Costanti.VALORE_PAGINA_ATTUALE_PROFILO)) {

				resp.sendRedirect(req.getContextPath()+"/profilo");
			}
			//else if(pagina.equals("ProfiloGenerico")) {
			else if(pagina.equals(Costanti.VALORE_PAGINA_ATTUALE_PROFILOGENERICO)) {
				resp.sendRedirect(req.getContextPath()+"/profiloGenerico");
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/Home");
			}
		}
		catch(CommentoNonValidoException e) {
			e.printStackTrace();
			//chiave errore login costante	
			req.setAttribute(Costanti.CHIAVE_ERRORE_COMMENTO, e.getMessage());
			//da un problema di no session se proviamo a reindirizzare altrove
			req.getRequestDispatcher("/jsp/pubblico/Errore.jsp").forward(req, resp);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

