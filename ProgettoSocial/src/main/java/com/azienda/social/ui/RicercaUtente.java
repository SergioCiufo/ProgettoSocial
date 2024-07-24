package com.azienda.social.ui;

import java.io.IOException;
import java.util.List;

import com.azienda.social.model.Utente;
import com.azienda.social.service.Service;
import com.azienda.social.utils.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ricercaUtente")
public class RicercaUtente extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			String ricerca=req.getParameter(Costanti.HTML_FORM_RICERCA);
			if(ricerca==null|| ricerca.isBlank()) {
				req.getRequestDispatcher("jsp/privato/Home.jsp").forward(req, resp);
			}else { 
				List<Utente> utentiTrovati=service.findbyName(ricerca);
				req.setAttribute(Costanti.CHIAVE_UTENTI_TROVATI, utentiTrovati);
				req.getRequestDispatcher("jsp/privato/RicercaProfilo.jsp").forward(req, resp);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/pubblico/Errore.jsp").forward(req, resp);
		}	
	}
}
