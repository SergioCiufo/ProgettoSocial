<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Errore</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
  <link rel="stylesheet" href="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/Errore.css" %>" />
</head>

<body>
  <div class="container-fluid d-flex flex-column min-vh-100">
    <!-- d-flex flex-column min-vh-100: mette il footer in fondo alla pagina-->
    <header class="row d-flex justify-content-between align-items-center">
      <div class="col-4 d-flex justify-content-center">
        <img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>" id="logoNav" />
      </div>
      <div class="col-4 d-flex justify-content-center">
        <h1>D.BUG</h1>
      </div>
      <div class="col-4"></div>
    </header>
    <div class="mb-4"></div>
    <section id="log" class="mt-4 mb-4 flex-grow-1">
      <div class="mb-3 text-center">
        <img src="<%= "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/img/Default.png" %>" id="logo" />
      </div>
      <div class="col-12 col-md-6 mx-auto">
        <div class="mb-3 text-center">
          <h1>QUALCOSA E' ANDATO STORTO</h1>
        </div>
        <div class="mt-3 mb-3 text-center">
          <div class="mb-3 text-center">
            <a href="http://localhost:8080/ProgettoSocial/jsp/pubblico/Welcome.jsp">Torna Indietro</a>
          </div>
        </div>
    </section>
    <div class="mb-4"></div>
    <footer class="row border mt-auto">
      <div class="col-12 d-flex justify-content-center">
        <h1>&copy;By Jaita119</h1>
      </div>
    </footer>
  </div>
</body>

</html>