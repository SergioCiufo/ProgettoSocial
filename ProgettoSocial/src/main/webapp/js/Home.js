//JQUERY PER APERTURA DIV COMMENTI
$(document).ready(function () {
    $(".toggleCommenti").click(function () {
        var commentiPost = $(this).closest(".col-8").find(".commenti-post"); //cerca il dato più vicino
        commentiPost.toggle(); // Mostra o nascondi i commenti relativi al post cliccato
    });
});
//-----------------------------------------//

//data corrente 
// Ottenere la data corrente
var now = new Date();

// Ottenere tutti gli elementi con la classe postDateTime
var postElements = document.getElementsByClassName("postDateTime");

// Iterare su tutti gli elementi trovati
for (var i = 0; i < postElements.length; i++) {
    var postElement = postElements[i];
    
    // Ottenere il valore dell'attributo data-time
    var dateString = postElement.getAttribute("data-time");
    
    // Formattare la data nel formato desiderato (yyyy-MM-ddTHH:mm:ss)
    var formattedDate = dateString.substring(0, 19);
    
    // Creare un oggetto data dal valore formattato
    var postTime = new Date(formattedDate);
    // Calcolare la differenza in millisecondi tra la data corrente e quella del post
    var differenceMs = now - postTime;
    
    // Calcolare minuti, ore e giorni di differenza
    var minuti = Math.floor(differenceMs / (1000 * 60));
    var ore = Math.floor(differenceMs / (1000 * 60 * 60));
    var giorni = Math.floor(differenceMs / (1000 * 60 * 60 * 24));
    
    // Determinare il testo da visualizzare in base alla differenza di tempo
    var testo;
    if (minuti < 1) {
        testo = "Ora";
    } else if (minuti < 60) {
        testo = minuti === 1 ? "1 Minuto fa" : minuti + " Minuti fa";
    } else if (ore < 24) {
        testo = ore === 1 ? "1 Ora fa" : ore + " Ore fa";
    } else {
        testo = giorni === 1 ? "1 Giorno fa" : giorni + " Giorni fa";
    }
    
    // Mostrare l'orario nel tuo elemento HTML
    postElement.textContent = testo;
}










//JAVASCRIPT PER DARK/LIGHT MODE + CAMBIO IMMAGINE
// Leggi lo stato del tema salvato in localStorage all'avvio della pagina
document.addEventListener("DOMContentLoaded", function () {
    var theme = localStorage.getItem("theme");
    if (theme === "dark") {
        document.body.classList.add("invertito");
        var postColors = document.getElementsByClassName("postColor");
        for (var i = 0; i < postColors.length; i++) {
            postColors[i].classList.add("invertito");
        }
        document.getElementById("btnImg").src ="http://LocalHost:8080/ProgettoSocial/img/DarkMode.png"
    } else {
        document.body.classList.remove("invertito");
        var postColors = document.getElementsByClassName("postColor");
        for (var i = 0; i < postColors.length; i++) {
            postColors[i].classList.remove("invertito");
        }
        document.getElementById("btnImg").src = "http://LocalHost:8080/ProgettoSocial/img/LightMode.png"
    }
});

// Gestisci il click sul pulsante per cambiare il tema
document.getElementById("cambiaColoreBtn").addEventListener("click", function () {
    var body = document.body;
    var postColors = document.getElementsByClassName("postColor");
    body.classList.toggle("invertito");
    for (var i = 0; i < postColors.length; i++) {
        postColors[i].classList.toggle("invertito");
    }
    // Salva lo stato del tema in localStorage
    if (body.classList.contains("invertito")) {
        localStorage.setItem("theme", "dark");
        document.getElementById("btnImg").src ="http://LocalHost:8080/ProgettoSocial/img/DarkMode.png"
    } else {
        localStorage.setItem("theme", "light");
        document.getElementById("btnImg").src = "http://LocalHost:8080/ProgettoSocial/img/LightMode.png";
    }
});
//-----------------------------------------------------------------//
//mostra e/o nasconde il bottone quando si sale e/o scende
$(window).scroll(function () {
    if ($(window).scrollTop() > 500) {
        $("#btnTop").css("display", "block");
    } else {
        $("#btnTop").css("display", "none");
    }
});

//click bottone che ritorna in alto
$("#btnTop").click(function () {
    $("html, body").scrollTop(0);
});

//anteprima immagine in JS
document.getElementById("imgInput").addEventListener("change", function (event) {
    var previewContainer = document.querySelector(".divAnteprima");
    var previewImage = document.querySelector(".immagineAnteprima");

    // Controlla se è stato selezionato un file
    if (event.target.files.length > 0) {
        var file = event.target.files[0]; // Prende il primo file dall'array dei file selezionati
        var reader = new FileReader(); // Crea un nuovo oggetto FileReader per leggere il contenuto del file

        // Quando l'immagine è stata completamente caricata
        reader.onload = function (e) {
            previewImage.src = e.target.result; // Imposta l'URL dell'immagine di anteprima
            previewContainer.style.display = "block"; // Mostra il div di anteprima
        }

        reader.readAsDataURL(file); // Avvia la lettura del file come dati URL
    } else {
        // Se non c'è nessun file selezionato
        previewImage.src = "#"; // Resetta l'URL dell'immagine di anteprima
        previewContainer.style.display = "none"; // Nasconde il div di anteprima
    }
});

//chisura Crea post
document.addEventListener("DOMContentLoaded", function () {
    var closeButton = document.getElementById("chiudiCreaPost");

    closeButton.addEventListener("click", function () {
        var creaPostDiv = document.getElementById("creaPostDiv");
        creaPostDiv.style.display = "none";
    });
});

//apri Crea post2
document.addEventListener("DOMContentLoaded", function () {
    var openButton = document.getElementById("openCreaPost1");

    openButton.addEventListener("click", function () {
        var creaPostDiv = document.getElementById("creaPostDiv");
        creaPostDiv.style.display = "block";
    });
});


//apri Crea post2
document.addEventListener("DOMContentLoaded", function () {
    var openButton = document.getElementById("openCreaPost2");

    openButton.addEventListener("click", function () {
        var creaPostDiv = document.getElementById("creaPostDiv");
        creaPostDiv.style.display = "block";
    });
});
