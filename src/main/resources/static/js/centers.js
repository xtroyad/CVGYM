var filtro = document.getElementById("filter");

var lista = document.getElementById("list");

filtro.addEventListener("change", function () {
    var opcionSeleccionada = filtro.value;


    if (opcionSeleccionada === "todos") {
        for (var i = 0; i < lista.children.length; i++) {
            lista.children[i].style.display = "block";
        }
    }else{
        for (var i = 0; i < lista.children.length; i++) {

            if(lista.children[i].classList.value === opcionSeleccionada){

                lista.children[i].style.display = "block";
            }else{
                lista.children[i].style.display = "none";
            }

        }
    }


});
