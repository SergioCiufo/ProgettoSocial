package com.azienda.social.ui;

import java.io.IOException;

import com.azienda.social.exception.RegistrazioneEmailEsistenteException;
import com.azienda.social.exception.RegistrazioneUsernameEsistenteException;
import com.azienda.social.service.Service;
import com.azienda.social.utils.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registrati")
public class RegistrazioneServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			String user=req.getParameter(Costanti.HTML_FORM_REGISTRAZIONE_USER);
			String password=req.getParameter(Costanti.HTML_FORM_REGISTRAZIONE_PASSWORD);
			String email=req.getParameter(Costanti.HTML_FORM_REGISTRAZIONE_EMAIL);
			String ddn=req.getParameter(Costanti.HTML_FORM_REGISTRAZIONE_DDN);
			String nome=req.getParameter(Costanti.HTML_FORM_REGISTRAZIONE_NOME);
			String cognome=req.getParameter(Costanti.HTML_FORM_REGISTRAZIONE_COGNOME);
			String control=null;
			
			//if(ddn!=null) { //NON SERVE POICHE' QUESTO DATO PUO' ESSERE NULL
				control=service.registrazioneUtente(user,password,email,ddn,nome,cognome);
			/*}
			else {
				control=service.registrazioneUtente(user,password,email,nome,cognome);
			}*/
			/*
			if(control!=null && control.startsWith("Username")) {
				req.setAttribute("ErroreUsername", control);
				req.getRequestDispatcher("/jsp/pubblico/Registrati.jsp").forward(req, resp);
			}
			else if(control!=null && control.startsWith("Email")) {
				req.setAttribute("ErroreEmail", control);
				req.getRequestDispatcher("/jsp/pubblico/Registrati.jsp").forward(req, resp);
			} else {
			*/
				req.getRequestDispatcher("/jsp/pubblico/Welcome.jsp").forward(req, resp);
			//}
			
			
		}
		catch(RegistrazioneUsernameEsistenteException e) {
			e.printStackTrace();
							//chiave errore login costante	
			req.setAttribute(Costanti.CHIAVE_ERRORE_REGISTRAZIONE_USERNAME, e.getMessage());
			req.getRequestDispatcher("/jsp/pubblico/Registrati.jsp").forward(req, resp);
		}
		catch(RegistrazioneEmailEsistenteException e) {
			e.printStackTrace();
							//chiave errore login costante	
			req.setAttribute(Costanti.CHIAVE_ERRORE_REGISTRAZIONE_EMAIL, e.getMessage());
			req.getRequestDispatcher("/jsp/pubblico/Registrati.jsp").forward(req, resp);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/pubblico/Errore.jsp").forward(req, resp);
		}
	}
}
