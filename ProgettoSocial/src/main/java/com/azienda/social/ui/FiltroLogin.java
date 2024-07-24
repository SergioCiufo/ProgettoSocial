package com.azienda.social.ui;

import java.io.IOException;

import com.azienda.social.model.Utente;
import com.azienda.social.utils.Costanti;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
@WebFilter("/jsp/privato/*")
public class FiltroLogin implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		try {
			HttpServletRequest httpRequest=(HttpServletRequest) arg0;
			//Utente utente=(Utente)httpRequest.getSession().getAttribute("chiaveSessione"); PRIMA, MEGLIO LAVORARE CON LE COSTANTI
			Utente utente=(Utente)httpRequest.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			
			if(utente != null) {
				arg2.doFilter(arg0, arg1); //fa continuare il reindirizzamento
			} else {
				//se i controlli non vanno bene, lo rimando al login
				arg0.getRequestDispatcher("/jsp/pubblico/Welcome.jsp").forward(arg0, arg1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			arg0.getRequestDispatcher("/jsp/pubblico/Welcome.jsp").forward(arg0, arg1);
		}
		
	}

}
