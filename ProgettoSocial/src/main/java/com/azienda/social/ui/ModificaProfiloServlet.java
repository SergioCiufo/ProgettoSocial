package com.azienda.social.ui;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import com.azienda.social.model.Utente;
import com.azienda.social.service.Service;
import com.azienda.social.utils.BlobConverter;
import com.azienda.social.utils.Costanti;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet("/modificaProfilo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 10,maxRequestSize = 1024 * 1024 * 10 * 5)
public class ModificaProfiloServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			String user=req.getParameter(Costanti.HTML_FORM_MODIFICA_USER);
			String password=req.getParameter(Costanti.HTML_FORM_MODIFICA_PASSWORD);
			String email=req.getParameter(Costanti.HTML_FORM_MODIFICA_EMAIL);
			String ddn=req.getParameter(Costanti.HTML_FORM_MODIFICA_DDN);
			String nome=req.getParameter(Costanti.HTML_FORM_MODIFICA_NOME);
			String cognome=req.getParameter(Costanti.HTML_FORM_MODIFICA_COGNOME); 
			String filePath = null;
			Blob immagine=null;
			String nomeImmagine=null;
			
			String uploadPath = getServletContext().getRealPath("") + File.separator + "nomeDirectory";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			for ( Part part : req.getParts() ) {
				String fileName = part.getSubmittedFileName();
				if ( fileName!=null && !fileName.isEmpty() ) {
					filePath = uploadPath + File.separator + fileName;
					part.write(filePath);
					immagine=BlobConverter.generateBlob(filePath);
					nomeImmagine=fileName;
				}
			}		

			Utente utenteAttivo=(Utente)req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			Utente utente=new Utente(user, password, nome, cognome, email, ddn);
			if(immagine!=null) {
				utente.setImmagine(immagine);
				utente.setNomeImmagine(nomeImmagine);
			}			
			service.modificaProfilo(utente,utenteAttivo);
			resp.sendRedirect(req.getContextPath()+"/profilo");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/jsp/pubblico/Errore.jsp").forward(req, resp);
		}
	}
}

