package com.azienda.social.ui;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//Service service=(Service)getServletContext().getAttribute("service"); PRIMA, MEGLIO LAVORARE CON LE COSTANTI
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			//String user=req.getParameter("un"); PRIMA, MEGLIO LAVORARE CON LE COSTANTI
			String user=req.getParameter(Costanti.HTML_FORM_LOGIN_USER_OR_EMAIL);
			//String password=req.getParameter("psw"); PRIMA, MEGLIO LAVORARE CON LE COSTANTI
			String password=req.getParameter(Costanti.HTML_FORM_LOGIN_PASSWORD);
			Utente utente=service.controlloLogin(user, password);
			//if(utente!=null) {
				//req.getSession().setAttribute("chiaveSessione", utente); PRIMA, MEGLIO LAVORARE CON LE COSTANTI
				req.getSession().setAttribute(Costanti.CHIAVE_SESSIONE_UTENTE, utente);
				//req.setAttribute("erroreLogin", "");
				resp.sendRedirect(req.getContextPath()+"/Home");
			//}
			/* meglio farlo gestire alle exception
			else {
				req.setAttribute("erroreLogin", "Credenziali non valide");
				req.getRequestDispatcher("/jsp/pubblico/Welcome.jsp").forward(req, resp);
			}
			 */
		}
		catch(CredenzialiNonValideException e) {
			e.printStackTrace();
							//chiave errore login costante	
			req.setAttribute(Costanti.CHIAVE_ERRORE_LOGIN, e.getMessage());
			req.getRequestDispatcher("/jsp/pubblico/Welcome.jsp").forward(req, resp);
		}

		catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/pubblico/Errore.jsp").forward(req, resp);
		}
	}
}
