package com.azienda.social.ui;

import java.io.IOException;
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
@WebServlet("/eliminaPost")
public class EliminaPostServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//Service service=(Service)getServletContext().getAttribute("service");
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			//String postid=req.getParameter("postIdElimina");
			String postid=req.getParameter(Costanti.HTML_FORM_ELIMINA_POST);
			Integer postId=Integer.parseInt(postid);
			//Utente utenteAttivo=(Utente)req.getSession().getAttribute("chiaveSessione");
			Utente utenteAttivo=(Utente)req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			service.eliminaPost(postId,utenteAttivo);	
			resp.sendRedirect(req.getContextPath()+"/profilo");
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/pubblico/Errore.jsp").forward(req, resp);
		}
	}
}
