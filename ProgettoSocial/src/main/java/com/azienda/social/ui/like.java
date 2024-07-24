package com.azienda.social.ui;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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
@WebServlet("/like")
public class like extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Service service=(Service)getServletContext().getAttribute("service");
		Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
		//String action = req.getParameter("action");
		String action = req.getParameter(Costanti.HTML_FORM_LIKE_NAME);
		//String id=req.getParameter("postId");
		String id=req.getParameter(Costanti.HTML_FORM_LIKE_POSTID);
		//Utente utente=(Utente)req.getSession().getAttribute("chiaveSessione");	
		Utente utente=(Utente)req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
		Integer idPost=Integer.parseInt(id);		
		try {							
			//if (action != null && action.equals("toggleLike")) { 		
			if (action != null && action.equals(Costanti.HTML_FORM_LIKE_VALUE)) { 	
				service.aggiungiLike(utente, idPost);
				//String pagina=(String)req.getSession().getAttribute("PaginaAttuale");
				String pagina=(String)req.getSession().getAttribute(Costanti.CHIAVE_PAGINA_ATTUALE);
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
		}

		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/pubblico/Errore.jsp").forward(req, resp);
		}
	}
}
