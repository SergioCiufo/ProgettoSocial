<%@page import="com.azienda.social.utils.Costanti"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.social.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Risultati ricerca</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
    <link rel="stylesheet" href="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/RicercaProfilo.css" %>" />
</head>

<body>
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
        <div class="row my-4">
            <div class="col-4"></div>
            <div class="col-4">
                <div class="row my-4">
                    <div class="col-12 text-center">
                        <h1>Risultati ricerca</h1>
                    </div>
                    <%
                    List<Utente> listaRicerca = (List<Utente>) request.getAttribute(Costanti.CHIAVE_UTENTI_TROVATI);
                    Map<Integer,String> mappaImmagineUtenti=(Map<Integer,String>)request.getSession().getAttribute("mappaImmaginiUtenti");
                    if (listaRicerca == null || listaRicerca.isEmpty()) {
                    %>
                    <div class="col-12" id="EmptyRisultati">
                        <%="Nessun Risultato Trovato" %>
                    </div>
                    <% } %>
                    <div class="col-12 text-center mb-3">
                        <a href="<%=request.getContextPath() + "/Home"%>" class="text-primary">Torna alla home</a>
                    </div>
                </div>
            </div>
            <div class="col-4"></div>
        </div>
        <div>
            <%
            for (Utente u : listaRicerca) {
            %>
        </div>
        <section class="row mb-5">
            <div class="col-4"></div>
            <div class="col-4">
                <div class="row">
                    <div class="col-6 d-flex justify-content-end align-items-center">
                        <img src="<%=mappaImmagineUtenti.get(u.getId()) %>" width="100px">
                    </div>
                    <div class="col-6 d-flex align-items-center">
                       <form action="<%=request.getContextPath() + "/profiloGenerico"%>" method="post">
                       	<input type="hidden" name="<%=Costanti.HTML_FORM_ELENCO_PROFILO %>" value="<%=u.getId() %>">
                       <input type="submit" value="<%= u.getNome()+" " %> <%= u.getCognome() %>" style="background-color: transparent; border:  none; font-weight:  bold; font-size: x-large;">
                       </form>
                       
                    </div>
                </div>
            </div>
            <div class="col-4"></div>
        </section>
        <div>
            <%
            }
            %>
        </div>
        <footer class="row border mt-auto">
            <div class="col-12 d-flex justify-content-center">
                <h1>&copy; By Jaita119</h1>
            </div>
        </footer>
    </div>
</body>

</html>