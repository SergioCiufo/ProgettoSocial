<%@page import="com.azienda.social.utils.Costanti"%>
<%@page import="java.util.Map"%>
<%@page import="com.azienda.social.model.Utente"%>
<%@page import="com.azienda.social.utils.BlobConverter"%>
<%@page import="com.azienda.social.model.Commenti"%>
<%@page import="com.azienda.social.model.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Profilo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
  <link rel="stylesheet" href="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/Home.css" %>" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<% 
		//Utente utente=(Utente)request.getSession().getAttribute("chiaveSessione");
		Utente utente=(Utente)request.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE);
		Map<Integer,String> mappaImmagine=(Map<Integer,String>)request.getSession().getAttribute("mappaImmagini");
		Map<Integer,String> mappaImmagineUtenti=(Map<Integer,String>)request.getSession().getAttribute("mappaImmaginiUtenti");
		//request.getSession().setAttribute("PaginaAttuale","Profilo");
		request.getSession().setAttribute(Costanti.CHIAVE_PAGINA_ATTUALE, Costanti.VALORE_PAGINA_ATTUALE_PROFILO);
	%>
<body id="mode">
  <div class="container-fluid d-flex flex-column min-vh-100">

    <header class="row border">
      <div class="col-4 d-flex justify-content-center">
        <img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>" id="logoNav">
      </div>
      <div class="col-4 d-flex justify-content-center">
        <h1 style="color: black;">D.BUG</h1>
      </div>
      <div class="col-2 d-flex justify-content-end align-items-center">
        <!--<div class="col-2 d-none d-md-flex justify-content-start align-items-center">-->
          <form action="<%=request.getContextPath() + "/ricercaUtente"%>" method="post"> 
          <input type="text" name="<%=Costanti.HTML_FORM_RICERCA%>" placeholder="Cerca Profilo..." id="search" />
          
      </div>
      <div class="col-2 d-flex justify-content-start align-items-center">

          
          <button class="btn btn-outline-success" type="submit"> Cerca</button>
        </form>
      </div>
    </header>
    <!--ok-->
    <!-- INIZIO DIV CREAPOST-->
    <div class="row flex-grow-1" id="creaPostDiv" style="display: none;">


      <div class="col-12">
        <div class="row mt-2 mb-2" style="border-radius: 2%;">
          <div class="col-4">
          </div>
          <div class="col-4 d-flex justify-content-center">
            <h3 style="color: black;">Crea Post</h3>
          </div>
          <div class="col-4 d-flex justify-content-end">
            <button id="chiudiCreaPost" class="btn btn-danger">X</button>
          </div>
        </div>

        <div class="row ">
          <form action="<%=request.getContextPath() + "/creaPost"%>" method="post" enctype="multipart/form-data">
            <div class="col-12">
             <!--  <textarea id="textCreaPost" name="post" placeholder="A cosa stai pensando?"
                class="form-control"></textarea>  -->
             <textarea id="textCreaPost" name="<%=Costanti.HTML_FORM_POST%>" placeholder="A cosa stai pensando?"
                class="form-control"></textarea>
            </div>
        </div>
        <div class="row mt-2 mb-2">
          <div class="col-4">
            <input type="file" name="immagine" accept=".jpg, .jpeg, .png" class="form-control scomparsa"
              id="imgInput">
          </div>
          <div class="col-4 d-flex justify-content-center">
            <button type="submit" class="btn btn-primary">Crea</button>
          </div>
          </form>
        </div>
        <div class="row divAnteprima" style="display: none;">
          <div class="col-12 d-flex justify-content-center mt-2 mb-2 ">
            <img src="#" alt="Anteprima Immagine" class="img-fluid immagineAnteprima">
          </div>
        </div>
      </div>


    </div>
    <!-- FINE DIV CREAPOST-->



    <div class="row flex-grow-1">

      <!--Div  per la fixed della sidebar sotto-->
      <div class="col-2 " style="background-color: #DCC0FF;">
      </div>

      <!--SX SideBar-->
      <div class="col-2 " id="sidebar" style="background-color: #DCC0FF;">
        <div class="row">
          <div class="col-12 d-flex justify-content-center" id="sideColumn">
            <img src="<%=mappaImmagineUtenti.get(utente.getId()) %>" class="fotoProfilo">
          </div>
          <div class="col-12" style="text-align: center;">
            <h3><%=utente.getNome() +" "+ utente.getCognome() %></h3>
          </div>
          <div class="col-12" style="text-align: center;">
            <a href="<%=request.getContextPath() + "/Home"%>">
              <h4>Home</h4>
            </a>
          </div>
          <div class="col-12" style="text-align: center;">
            <a href="<%=request.getContextPath() + "/profilo"%>">
              <h4>Il Mio Profilo</h4>
            </a>
          </div>
          <div class="col-12" style="text-align: center;">
            <a href=<%=request.getContextPath() + "/logout"%>>
              <h4>Logout</h4>
            </a>
          </div>
          <div class="col-12 mt-5 mb-5" style="border-top: solid 1px black;">

          </div>
          <div class="col-12" style="text-align: center;">
            <h3>Link Utili</h3>
          </div>
          <div class="col-12" style="text-align: center;">
            <a href="https://www.reddit.com/?rdt=40763">
              <h4>Reddit</h4>
            </a>
          </div>
          <div class="col-12" style="text-align: center;">
            <a href="https://www.w3schools.com/">
              <h4>W3Schools</h4>
            </a>
          </div>
          <div class="col-12" style="text-align: center;">
            <a href="https://getbootstrap.com/">
              <h4>BootStrap</h4>
            </a>
          </div>
          <div class="col-12" style="text-align: center;">
            <a href="https://jquery.com/">
              <h4>jQuery</h4>
            </a>
          </div>

          <div class="col-12 mt-5 mb-5" style="border-top: solid 1px black;">

          </div>
          <div class="col-12" style="text-align: center;">
            <h5>Lovingly created by Group 4 of JAITA119 </h5>
          </div>
        </div>
      </div>
      <!--FINE SX SideBar-->
      <!--POST-->

      
      <div class="col-8 mb-3">
     <div class="row mt-3">
     
          <div class="col-4 d-flex align-items-center justify-content-end">
            <img class="fotoProfilo" src="<%=mappaImmagineUtenti.get(utente.getId())%>">
          </div>
          <div class="col-4 d-flex align-items-center justify-content-center">
            <h1> <%=utente.getNome() +" "+ utente.getCognome() %></h1>
          </div>
          <div class="col-3 d-flex align-items-center">
            <h3>@<%=utente.getUsername()%></h3>
          </div>
        </div>
        
        <div class="row"> <% List<Post> elencoPost=(List<Post>)request.getAttribute(Costanti.CHIAVE_ELENCO_POST_PERSONALI); %>
        <% 
          if ( elencoPost != null && elencoPost.size() > 0 )
        {
    
          for(Post p:elencoPost){
        %>
          <div class="col-12">

            <!-- RIGA post-->
            <div class="row ">
              <div class="col-2">
              </div>
              <!--Inizio Post-->
              <div class="col-8 mt-3 postColor"  style="border: solid 1px black; border-radius: 1%;">
                <div class="row">
                  <div class="col-3 mt-3 d-flex justify-content-center">
                    <img class="fotoProfilo" src="<%=mappaImmagineUtenti.get(p.getUtente().getId()) %>">
                  </div>
                  <div class="col-4 mt-3 d-flex align-items-center">
                    <div class="row">
                      <div class="col-12">
                        <h3><%=p.getUtente().getNome() %> <%=p.getUtente().getCognome() %></h3>
                      </div>
                      <div class="col-12">
                        <h6>@<%=p.getUtente().getUsername()%></h6>
                      </div>
                    </div>
                  </div>
                  <div class="col-3 mt-3 d-flex align-items-center justify-content-end postDateTime" 
                    data-time="<%=p.getData_post()%>">  <!--2024-06-30T10:00:00-->
                    <h6>Giorno/ora Pubblicazione</h6>
                  </div >
                  <div class="col-2 mt-3 d-flex align-items-center justify-content-end " >
                  	<% 
						if(utente.getId().equals(p.getUtente().getId())) 
							{
						%>
						<form action="<%= request.getContextPath() + "/eliminaPost" %>" method="post"> 
						<!-- <input type="hidden" name="postIdElimina" value="<//%= p.getId() %>">  -->
						<input type="hidden" name="<%=Costanti.HTML_FORM_ELIMINA_POST%>" value="<%= p.getId() %>">
						<input type="submit" class="btn btn-danger" value="Elimina"> 
						</form>
						<%} %>
                  </div>
                </div>
                 <%
                	if(mappaImmagine.get(p.getId())!=null){
                		          	
                %>
                <div class="row mt-3">
                  <div class="col-12 d-flex justify-content-center">
                    <img class="immaginePost"
                      src="<%=mappaImmagine.get(p.getId())%>">
                  </div>
                </div>
                <%	} %>
                <div class="row mt-3">
                  <div class="col-12">
                    <p><%=p.getTesto() %></p>
                  </div>
                </div>

                <!--REACT-->
                <div class="row mb-2">
                  <div class="col-6 d-flex align-items-center justify-content-start">
                    <h5 style="margin-right: 5px;"><% 
                      if(p.getListaLikers().size()>0){
                    %>
                    <%=p.getListaLikers().size() %>
                    <%
                      } 
                    %></h5>
                    
                    <%	String togglelike= "like.png";
                    	if(p.getListaLikers().contains(utente)){
                    		togglelike="likeCheck.png";
                    	}
                    %>
                    <form action="<%=request.getContextPath() + "/like"%>" method="post" style="width:20%;">
                    <!-- <input type="hidden" name="action" value="toggleLike">  -->
                    <input type="hidden" name="<%=Costanti.HTML_FORM_LIKE_NAME %>" value="<%=Costanti.HTML_FORM_LIKE_VALUE %>">
					<!-- <input type="hidden" name="postId" value="<//%= p.getId() %>">  -->
					<input type="hidden" name="<%=Costanti.HTML_FORM_LIKE_POSTID %>" value="<%= p.getId() %>"> 
                    <button id="like-button" onclick="toggleLike()">
                      <img id="like-button-img" src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/" + togglelike %>" style="width:400%;">
                    
                    </button>
                  </form>
                  <%	
                  String toggleDislike= "dislike.png";
                    	if(p.getListaDislikers().contains(utente)){
                    		toggleDislike="dislikeCheck.png";
                    	}
                    %>
                  <form action="<%=request.getContextPath() + "/dislike"%>" method="post" style="width:20%;">
                   <!--  <input type="hidden" name="actionD" value="toggleDislike">  -->
                    <input type="hidden" name="<%=Costanti.HTML_FORM_DISLIKE_NAME%>" value="<%=Costanti.HTML_FORM_DISLIKE_VALUE %>">
                   <!--  <input type="hidden" name="postIdD" value="<//%= p.getId() %>">  -->
                    <input type="hidden" name="<%=Costanti.HTML_FORM_DISLIKE_POSTID%>" value="<%= p.getId() %>"> 
                    <button id="dislike-button" onclick="toggleDislike()">
                      <img id="dislike-button-img" src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/" + toggleDislike %>" style="width:400%;">
                  
                    </button>
                  </form>
                  </div>
                  <!--FINE REACT-->
                  <!--ACCESSO AI COMMENTI-->
                  <div class="col-6 d-flex justify-content-end">
                    <button type="button" class="btn btn-primary toggleCommenti"
                      data-target=".commenti-post">Commenti</button>
                  </div>
                  <!--FINE ACCESSO AI COMMENTI-->
                </div>

                <!-- DIV COMMENTO UTENTE-->
                <% List<Commenti> elencoCommenti=(List<Commenti>)request.getAttribute(Costanti.CHIAVE_ELENCO_COMMENTI);
                  String erroreCommento=(String)request.getAttribute("erroreCommento");
                %>
                <%
                
                if ( elencoCommenti != null && elencoCommenti.size() > 0)
                {
                
                  for(Commenti c:elencoCommenti){
                  if(c.getPost().equals(p)){
                
                %>
                <div class="col-12 mt-3 commenti-post" style="display: none;">
                  <!-- COMMENTI-->
                  <div class="row mt-3 border-top commento">
                    <div class="col-2 mt-2 d-flex justify-content-center">
                      <img class="fotoCommento" src="<%=mappaImmagineUtenti.get(c.getUtente().getId())  %>">
                    </div>
                    <div class="col-4 d-flex align-items-center justify-content-start">
                      <h6><%=c.getUtente().getNome() +" "+c.getUtente().getCognome()%></h6>
					</div>
					<div class="col-4 d-flex align-items-center justify-content-start">
                      <h6><%="@"+c.getUtente().getUsername()%></h6>
					</div>
					<div class="col-2 d-flex align-items-center justify-content-end postDateTime" 
                    		data-time="<%=c.getData()%>"> <!--2024-06-30T10:00:00-->
                    <h6>Giorno/ora Pubblicazione</h6>
                  </div>
               	 </div>
                  <div class="row mb-3 border-bottom">
                    <div class="col-2">

                    </div>
                    <div class="col-10">
                      <p> <%=c.getTesto() %></p>
                    </div>
                  </div>
                  <!-- chiusura display none -->

                  <!--FINE COMMENTI UTENTE-->

                </div>
                  <% } }%> <!-- chiusura commenti -->
                  <% } %>
                <!-- FINE DIV COMMENTO UTENTE-->
               
                    <!--INSERISCI COMMENTO-->
                  <div class="row mt-3 mb-3">
                    <div class="col-10">
                       <form action="<%=request.getContextPath() + "/commento"%>" method="post">
                        <!--<input type="text" name="commentoPost" class="inserisciCommento" placeholder="Inserisci Commento"> -->
                        <input type="text" name="<%=Costanti.HTML_FORM_COMMENTA%>" class="inserisciCommento" placeholder="Inserisci Commento" required>
                        <!-- <input type="hidden" name="postIdC" value="<%= p.getId() %>"> -->
                        <input type="hidden" name="<%=Costanti.HTML_FORM_COMMENTA_ID_POST %>" value="<%= p.getId() %>"> 
                    </div>
                    <div class="col-2">
                      <button type="submit" class="btn btn-warning btnInvia"><img class="imgSend"
                          src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/sendComment.png" %>"></button>
                      </form>
                    </div>
                    
                  </div>
                   <!--FINE INSERISCI COMMENTO-->
              </div>
         	
              <!--Fine Post-->
              <div class="col-2">

              </div>  
            </div> 
            <% 
				}	
			%>
   			<%
				} 
			%>   
            <!-- Qui si chiude Riga post-->
          </div>         
        </div>
      </div>	
      <!--FINE POST-->
      <!--DX Sidebar-->
      <div class="col-2" id="dxSidebar">

		<div class="row mt-5 mb-3">
          	<div class="col-3">
          </div>
          <div class="col-6 d-flex justify-content-center">
            <a href="<%=request.getContextPath() + "/jsp/privato/ModificaProfilo.jsp"%>">
              <img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/modificaProfilo.png" %>" class="sideDxImg" style="border: none; background: none;">
          </a>
          </div>
          <div class="col-3">
          </div>
        </div>


        <div class="row ">
          <div class="col-3">
          </div>
          <div class="col-6 d-flex justify-content-center">
            <button id="openCreaPost1" style="background: none; border: none;"><img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/createPost.png" %>"
                class="sideDxImg"></button>
          </div>
          <div class="col-3">
          </div>

        </div>
        <div class="row">
          <div class="col-3">
          </div>
          <div class="col-6 d-flex justify-content-center">
            <button type="button" id="openCreaPost2" class="btn btn-primary">New Post</button>

          </div>
          <div class="col-3">
          </div>

        </div>
        <div class="row mt-5">
          <div class="col-3">
          </div>
          <div class="col-6">
            <button id="cambiaColoreBtn" style="background: none; border: none;">
              <img id="btnImg" src="<%= "http://LocalHost:8080/ProgettoSocial/img/LightMode.png" %>" class="sideDxImg">
            </button>
          </div>
          <div class="col-3">
          </div>
        </div>

        <div class="row mt-5">
          <div class="col-3">
          </div>
          <div class="col-6 d-flex justify-content-center">
            <button id="btnTop"><img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/topBtn.png" %>" width="50px"></button>
          </div>
          <div class="col-3">
          </div>
        </div>

      </div>
      <!--FINE DX SIDEBAR-->
    </div>

    


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<%="http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/js/Home.js" %>"></script>

</body>

</html>