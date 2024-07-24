package com.azienda.social.ui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import com.azienda.social.model.Commenti;
import com.azienda.social.model.Post;
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
@WebServlet("/creaPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 1024 * 1024 * 10,maxRequestSize = 1024 * 1024 * 10 * 5)
public class CreazionePost extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//Service service=(Service)getServletContext().getAttribute("service");
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			//String testo=req.getParameter("post");
			String testo=req.getParameter(Costanti.HTML_FORM_POST);
			//Utente utente=(Utente)req.getSession().getAttribute("chiaveSessione");
			Utente utente=(Utente)req.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
			
			String uploadPath = getServletContext().getRealPath("") + File.separator + "nomeDirectory";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String filePath = null;
			Blob immagine=null;
			String nomeImmagine=null;
			for ( Part part : req.getParts() ) {
				String fileName = part.getSubmittedFileName();
				if ( fileName!=null && !fileName.isEmpty() ) {
					filePath = uploadPath + File.separator + fileName;
					part.write(filePath);
					immagine=BlobConverter.generateBlob(filePath);
					nomeImmagine=fileName;
				}
			}
			service.creaPost(testo,utente,immagine,nomeImmagine);
			//String pagina=(String)req.getSession().getAttribute("PaginaAttuale");
			String pagina=(String)req.getSession().getAttribute(Costanti.CHIAVE_PAGINA_ATTUALE);
			
			//if(pagina.equals("Profilo")) {
			if(pagina.equals(Costanti.VALORE_PAGINA_ATTUALE_PROFILO)) {
				resp.sendRedirect(req.getContextPath()+"/profilo");
			 }
			 else {
				 resp.sendRedirect(req.getContextPath()+"/Home");
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/pubblico/Errore.jsp").forward(req, resp);
		}
	}
}
