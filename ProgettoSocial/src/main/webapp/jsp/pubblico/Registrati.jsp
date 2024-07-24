<%@page import="com.azienda.social.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrati</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/Registrati.css" %>">
</head>

<body>
	<div class="container-fluid">
		<header class="row border">
			<div class="col-4 d-flex justify-content-center">
				<img
					src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>"
					id="logoNav">
			</div>
			<div class="col-4 d-flex justify-content-center">
				<h1>D.BUG</h1>
			</div>
			<div class="col-4"></div>
		</header>
		<div class="row" id="mammeta">
			<section class="py-5 bg--bs-light--bs-light-rgb" id="registration">
				<div class="container">
					<h1 class="text-center mb-4">REGISTRATI</h1>
					<div class="text-center mb-4">
						<img
							src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>"
							alt="Logo" class="img-fluid" style="max-width: 150px;">
					</div>
					<div class="row justify-content-center">
						<div class="col-md-6">
							<form action="<%=request.getContextPath() + "/registrati"%>"
								method="post">

								<div class="mb-3">
									<!--mb allarga le immagini adattandole allo schermo-->
									<label for="nome" class="form-label">Nome*</label> <input
										type="text" class="form-control" id="nome" name="nomeR"
										placeholder="Inserisci il tuo nome" required>
								</div>
								<div class="mb-3">
									<label for="cognome" class="form-label">Cognome*</label> <input
										type="text" class="form-control" id="cognome" name="cognomeR"
										placeholder="Inserisci il tuo cognome" required>
								</div>
								<div class="mb-3">
									<label for="data-nascita" class="form-label">Data di
										nascita*</label> <input type="date" class="form-control"
										id="data-nascita" name="ddnR">
								</div>
								<div class="errore">
									<% String controlloU=(String)request.getAttribute(Costanti.CHIAVE_ERRORE_REGISTRAZIONE_USERNAME); %>
									<% String controlloE=(String)request.getAttribute(Costanti.CHIAVE_ERRORE_REGISTRAZIONE_EMAIL); %>
									<% if(controlloU!=null){ %>
									<%=controlloU %>
									<%} %>
								</div>
								<div class="mb-3">
									<label for="username" class="form-label">Username*</label> <input
										type="text" class="form-control" id="username" name="unR"
										placeholder="Inserisci il tuo username" required>
								</div>
								<div class="mb-3">
									<label for="password" class="form-label">Password*</label> <input
										type="password" class="form-control" id="password" name="pswR"
										placeholder="Inserisci la tua password" required>
								</div>
								<div class="errore">
									<% if(controlloE!=null){ %>
									<%=controlloE %>
									<%} %>
								</div>
								<div class="mb-3">
									<label for="email" class="form-label">E-Mail*</label> <input
										type="email" class="form-control" id="email" name="emailR"
										placeholder="Inserisci la tua email" required>
								</div>
								<button type="submit" class="btn btn-success">Registrati</button>
								<div class="row mt-3">
									<!--mt-*: Gestisce il margine superiore-->
									<div class="col-12 d-flex justify-content-center">
										<a
											href="http://localhost:8080/ProgettoSocial/jsp/pubblico/Welcome.jsp">Hai
											già un account?</a>
										<!--DA SISTEMARE LINK-->
									</div>
								</div>
							</form>
						</div>
			</section>
		</div>
		<footer class="row border">
			<div class="col-12 d-flex justify-content-center">
				<h1>&copy;By Jaita119</h1>
			</div>
		</footer>
	</div>
</body>

</html>