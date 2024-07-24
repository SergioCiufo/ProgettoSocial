<%@page import="com.azienda.social.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ProgettoFinale_Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
    <link rel="stylesheet" href="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/Login.css" %>" />
</head>

<body>
    <div class="container-fluid">
        <header class="row d-flex justify-content-between align-items-center">

            <div class="col-4 d-flex justify-content-center">
                <img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>" id="logoNav" />
            </div>
            <div class="col-4 d-flex justify-content-center">
                <h1>D.BUG</h1>
            </div>
            <div class="col-4"></div>

        </header>

        <section id="log" class="col-12 col-md-6 mx-auto ">

            <div class="mb-3 text-center">
                <h1>BENVENUTO</h1>
            </div>
            <div class="mb-3 text-center">
                <img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>" id="logo" />
            </div>
            <div class="mb-3 text-center">
                <div id="errore"> 
                    <% String erroreLogin=(String)request.getAttribute(Costanti.CHIAVE_ERRORE_LOGIN); %>
                    <%if(erroreLogin==null){
                        erroreLogin="";}
                        %>
                    
                    <%=erroreLogin %>
                </div>
                <form action="<%=request.getContextPath() + "/login"%>" method="post">
                
                    <!--  SENZA COSTANTE AL NAME
                    <input type="text" name="un" placeholder="Username" class="form-control mx-auto"
                        style="max-width: 300px;" />
                    -->    
                    <input type="text" name="<%=Costanti.HTML_FORM_LOGIN_USER_OR_EMAIL%>" placeholder="Username" class="form-control mx-auto"
                        style="max-width: 300px;" />
               
            </div>
            <div class="mb-3 text-center">
            
                <!--   SENZA COSTANTE AL NAME
                 <input type="password" name="psw" placeholder="Password" class="form-control mx-auto"
                    style="max-width: 300px;" />
                -->
                <input type="password" name="<%=Costanti.HTML_FORM_LOGIN_PASSWORD%>" placeholder="Password" class="form-control mx-auto"
                    style="max-width: 300px;" />

            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-success">Login</button>
                <br>
                <br>
                </form>
                <div class="mb-3 text-center">
                    <h3>Non sei registrato?</h3>
                </div>
                <div class="mb-3 text-center">
                    <a href="http://localhost:8080/ProgettoSocial/jsp/pubblico/Registrati.jsp">Registrati</a>
                </div>
            </div>

        </section>

        <footer class="row">
            <div class="col-12 text-center">
                <h1>By Jaita119</h1>
            </div>
        </footer>

    </div>
</body>