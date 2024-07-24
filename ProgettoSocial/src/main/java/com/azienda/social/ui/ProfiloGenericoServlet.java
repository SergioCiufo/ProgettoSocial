package com.azienda.social.ui;

import java.io.IOException;
import java.util.List;

import com.azienda.social.model.Commenti;
import com.azienda.social.model.Utente;
import com.azienda.social.service.Service;
import com.azienda.social.utils.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/profiloGenerico")
public class ProfiloGenericoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			try {
				Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
				if(req.getParameter(Costanti.HTML_FORM_ELENCO_PROFILO)!=null) {
					Integer idUtente=(Integer)Integer.parseInt(req.getParameter(Costanti.HTML_FORM_ELENCO_PROFILO));
					Utente utenteRicercato=service.findUtentebyId(idUtente);
					req.getSession().setAttribute(Costanti.CHIAVE_UTENTE_RICERCATO, utenteRicercato);
					}
					List<Commenti> elencoCommenti=service.tuttiCommenti();
					req.setAttribute(Costanti.CHIAVE_ELENCO_COMMENTI, elencoCommenti);
					req.getRequestDispatcher("/jsp/privato/ProfiloGenerico.jsp").forward(req, resp);
				
	
			} 
			catch (Exception e) {
				e.printStackTrace();
				req.getRequestDispatcher("jsp/pubblico/Errore.jsp").forward(req, resp);
			}
		}
	}
