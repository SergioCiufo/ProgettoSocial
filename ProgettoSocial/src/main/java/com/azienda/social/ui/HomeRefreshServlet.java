package com.azienda.social.ui;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@WebServlet("/Home")

public class HomeRefreshServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//Service service=(Service)getServletContext().getAttribute("service");
			Service service=(Service)getServletContext().getAttribute(Costanti.CHIAVE_SERVICE);
			//List<Post> elencoPost=service.tuttiPost();
			//all'apertura della pagina in filtro null ordina per più recente
			List<Post> elencoPost=service.ordinaByPiùRecente();
			req.setAttribute(Costanti.CHIAVE_ELENCO_POST, elencoPost);
			List<Commenti> elencoCommenti=service.tuttiCommenti();
			req.setAttribute(Costanti.CHIAVE_ELENCO_COMMENTI, elencoCommenti);
			mappaImmagini(req, elencoPost);
			List<Utente> listaUtenti=service.tuttiUtenti();
			mappaImmaginiUtente(req, listaUtenti);
			
			//ci prendiamo il dato della select "ordina post"
			String filtroPost=req.getParameter(Costanti.HTML_FORM_SELECT_ORDINAPOST);
			//se ordina post non è nullo ed equivale alla select "piùLike"
			if(filtroPost!=null && filtroPost.equals(Costanti.HTML_FORM_SELECT_ORDINAPOST_PIULIKE)) {
			//allora chiama il metodo ordinaByLike e sovrascrive l'elencoPost nell'ordine selezionato
				elencoPost=service.ordinaByLike();
			//sovrascriviamo la req.attribute di elencPost
				req.setAttribute(Costanti.CHIAVE_ELENCO_POST, elencoPost);
			}
			
			if(filtroPost!=null && filtroPost.equals(Costanti.HTML_FORM_SELECT_ORDINAPOST_PIURECENTE)) {
				elencoPost=service.ordinaByPiùRecente();
				req.setAttribute(Costanti.CHIAVE_ELENCO_POST, elencoPost);
			}
			
			if(filtroPost!=null && filtroPost.equals(Costanti.HTML_FORM_SELECT_ORDINAPOST_MENORECENTE)) {
				elencoPost=service.tuttiPost();
				req.setAttribute(Costanti.CHIAVE_ELENCO_POST, elencoPost);
			}
			
			
			
			if(filtroPost!=null) {
				req.getSession().setAttribute(Costanti.HTML_FORM_SELECT_ORDINAPOST, filtroPost);
			} 
			
			
			req.getRequestDispatcher("jsp/privato/Home.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("jsp/pubblico/Errore.jsp").forward(req, resp);
		}		
	}
	private void mappaImmagini(HttpServletRequest req,List<Post> listaPost) throws Exception {
		Map<Integer, String> mappaImmagini=new HashMap<Integer, String>();
		
		String uploadPath = getServletContext().getRealPath("") + File.separator + "nomeDirectory";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		String baseHttpUrl="http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();
		for(Post p:listaPost) {
			Blob immagine=p.getImmagine();
			if(immagine!=null) {
				String filePath=uploadPath+File.separator+p.getId()+"_"+p.getNomeImmagine();
				BlobConverter.saveFile(immagine, filePath);
				mappaImmagini.put(p.getId(), baseHttpUrl+"/nomeDirectory/"+p.getId()+"_"+p.getNomeImmagine());
			}
		/*	else {
				mappaImmagini.put(p.getId(), baseHttpUrl+"/img/Default.png");
			}*/
		}
		req.getSession().setAttribute("mappaImmagini", mappaImmagini);
	}
	public void mappaImmaginiUtente(HttpServletRequest req,List<Utente> listaUtenti) throws Exception {
		Map<Integer, String> mappaImmagini=new HashMap<Integer, String>();
		
		String uploadPath = getServletContext().getRealPath("") + File.separator + "nomeDirectory";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		String baseHttpUrl="http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath();
		for(Utente u:listaUtenti) {
			Blob immagine=u.getImmagine();
			if(immagine!=null) {
				String filePath=uploadPath+File.separator+u.getId()+"_"+u.getNomeImmagine();
				BlobConverter.saveFile(immagine, filePath);
				mappaImmagini.put(u.getId(), baseHttpUrl+"/nomeDirectory/"+u.getId()+"_"+u.getNomeImmagine());
			}
			else {
				mappaImmagini.put(u.getId(), baseHttpUrl+"/img/DefaultProfilo.png");
			}
		}
		req.getSession().setAttribute("mappaImmaginiUtenti", mappaImmagini);
	}
}
