package com.azienda.social.ui;

import java.io.IOException;

import com.azienda.social.service.Service;
import com.azienda.social.utils.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/logout")
public class Logout extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.getSession().removeAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			req.getRequestDispatcher("/jsp/pubblico/Welcome.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/pubblico/Errore.jsp").forward(req, resp);
		}
	}
}
