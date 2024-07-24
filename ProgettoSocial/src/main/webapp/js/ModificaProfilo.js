
//click scomparsa div JQUERY
$(document).ready(function() {
    $(".toggleBtn").click(function() {
        $(this).closest(".mb-3").find(".scomparsa").toggle();
    });
});

//anteprima immagine in JS
document.getElementById("imgInput").addEventListener("change", function(event) {
    var previewContainer = document.querySelector(".divAnteprima");
    var previewImage = document.querySelector(".immagineAnteprima");
        //controllo se è stato messo il file
    if (event.target.files.length > 0) { //se l'array ha un elemento
        var file = event.target.files[0]; //prende l'elemento dall'array
        var reader = new FileReader(); //creazione nuovo oggetto per lettura immagine

        reader.onload = function(e) { //quando l'ìmg è stata completamente caricata
            previewImage.src = e.target.result; //indica url dell'immagine
            previewContainer.style.display = "block"; // Mostra il div se viene inserita l'immagine
        }

        reader.readAsDataURL(file); //avvia la lettura del file come dati url
        //se non c'è nessun file
    } else {
        previewImage.src = "#"; //se src resta così allora display none
        previewContainer.style.display = "none"; // Nasconde il div se non viene inserita l'immagine
    }
});