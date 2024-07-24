package com.azienda.social.ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.azienda.social.dao.CommentiDao;
import com.azienda.social.dao.PostDao;
import com.azienda.social.dao.UtenteDao;
import com.azienda.social.service.Service;
import com.azienda.social.utils.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(value="/init",loadOnStartup = 1 )
public class Inizializzazione extends HttpServlet{
	@Override
	public void init() throws ServletException {
		EntityManager manager=null;	
		try {
			EntityManagerFactory factory=Persistence.createEntityManagerFactory(Costanti.PERSISTENCE);
			manager =factory.createEntityManager();
			System.out.println("connessione OK");
			UtenteDao utentiDao=new UtenteDao(manager);
			CommentiDao commentiDao=new CommentiDao(manager);
			PostDao postDao=new PostDao(manager);
			Service service=new Service(manager, utentiDao, commentiDao, postDao);
			getServletContext().setAttribute(Costanti.CHIAVE_SERVICE, service);	
			System.out.println("Server Pronto a funzionare");
		} catch (Exception e) {
			e.printStackTrace();
			manager.close(); 
			System.exit(0);
			
		}	
		
	}
}
