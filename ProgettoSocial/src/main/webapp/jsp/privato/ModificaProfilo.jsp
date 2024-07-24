<%@page import="com.azienda.social.utils.Costanti"%>
<%@page import="com.azienda.social.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Modifica Profilo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/ModificaProfilo.css" %>">

						
</head>
<body>
	<% Utente utente=(Utente)request.getSession().getAttribute(Costanti.CHIAVE_SESSIONE_UTENTE); %>

	<div class="container-fluid d-flex flex-column min-vh-100">
		<header class="row border">
			<div class="col-4 d-flex justify-content-center">
				<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>" id="logoNav">
			</div>
			<div class="col-4 d-flex justify-content-center">
				<h1>D.BUG</h1>
			</div>
			<div class="col-4"></div>
		</header>
		<div class="row" id="mammeta">
			<section class="py-5 bg--bs-light--bs-light-rgb" id="registration">
				<div class="container">
					<div class="text-center mb-4">
						<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>" alt="Logo" class="img-fluid"
							style="max-width: 150px;">
					</div>
					<h1 class="text-center mb-4">MODIFICA PROFILO</h1>

					<div class="row justify-content-center">
						<div class="col-md-6 border">

							<form action="<%=request.getContextPath() + "/modificaProfilo"%>"
								method="post" enctype='multipart/form-data'>

								<div class="container">
									<div class="mb-3">
										<button type="button" class="btn btn-transparent toggleBtn">
											<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Pencil.png" %>" width="30px">
										</button>
										<label for="nome" class="form-label">Nome: <%=utente.getNome() %></label>
										<input type="text" name="<%=Costanti.HTML_FORM_MODIFICA_NOME%>"
											class="form-control scomparsa" id="nome"
											placeholder="Inserisci il nuovo nome" style="display: none;">
									</div>

									<div class="mb-3">
										<button type="button" class="btn btn-transparent toggleBtn">
											<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Pencil.png" %>" width="30px">
										</button>
										<label for="cognome" class="form-label">Cognome: <%=utente.getCognome() %></label>
										<input type="text" name="<%=Costanti.HTML_FORM_MODIFICA_COGNOME%>"
											class="form-control scomparsa" id="cognome"
											placeholder="Inserisci il nuovo cognome"
											style="display: none;">
									</div>

									<div class="mb-3">
										<button type="button" class="btn btn-transparent toggleBtn">
											<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Pencil.png" %>" width="30px">
										</button>
										<label for="data-nascita" class="form-label">Data di
											nascita: <%=utente.getDdn()%></label> <input type="date"
											name="<%=Costanti.HTML_FORM_MODIFICA_DDN%>" class="form-control scomparsa"
											id="data-nascita" style="display: none;">
									</div>
									<div class="mb-3">
										<button type="button" class="btn btn-transparent toggleBtn">
											<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Pencil.png" %>" width="30px">
										</button>
										<label for="username" class="form-label">Username: <%=utente.getUsername()%></label>
										<input type="text" name="<%=Costanti.HTML_FORM_MODIFICA_USER%>"
											class="form-control scomparsa" id="username"
											placeholder="Inserisci il nuovo username"
											style="display: none;">
									</div>
									<div class="mb-3">
										<button type="button" class="btn btn-transparent toggleBtn">
											<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Pencil.png" %>" width="30px">
										</button>
										<label for="password" class="form-label">Password: <%=utente.getPassword()%></label>
										<input type="password" name="<%=Costanti.HTML_FORM_MODIFICA_PASSWORD%>"
											class="form-control scomparsa" id="password"
											placeholder="Inserisci la nuova password"
											style="display: none;">
									</div>
									<div class="mb-3">
										<button type="button" class="btn btn-transparent toggleBtn">
											<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Pencil.png" %>" width="30px">
										</button>
										<label for="email" class="form-label">E-Mail: <%=utente.getEmail()%></label>
										<input type="email" name="<%=Costanti.HTML_FORM_MODIFICA_EMAIL%>"
											class="form-control scomparsa" id="email"
											placeholder="Inserisci la nuova email" style="display: none;">
									</div>
									<div class="mb-3">
										<button type="button" class="btn btn-transparent toggleBtn">
											<img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Pencil.png" %>" width="30px">
										</button>
										<label for="imgInput" class="form-label">Immagine
											Profilo</label> <input type="file" name="immagineMod"
											accept=".jpg, .jpeg, .png" class="form-control scomparsa"
											id="imgInput" style="display: none;">
									</div>

									<div class="divAnteprima mt-3" style="display: none;">
										<img src="#" alt="Anteprima Immagine"
											class="img-fluid immagineAnteprima">
									</div>
									<button type="submit" class="btn btn-success">Applica</button>
									<a href="<%=request.getContextPath() + "/profilo"%>" class="btn btn-danger">Annulla</a>
									<div class="row mt-3">
										<!--mt-*: Gestisce il margine superiore-->
									</div>
							</form>
							
						</div>
			</section>
		</div>
		<div class="mb-4" id="patet"></div>
		<footer class="row border mt-auto">
			<div class="col-12 d-flex justify-content-center">
				<h1>&copy;By Jaita119</h1>
			</div>
		</footer>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/js/ModificaProfilo.js" %>"></script>
</body>

</html>