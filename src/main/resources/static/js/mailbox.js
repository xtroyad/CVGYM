const filtro = document.getElementById("typeQuestion");

const lista = document.getElementById("list");

filtro.addEventListener("change", function () {
    const opcionSeleccionada = filtro.value;


    if (opcionSeleccionada === "todos") {
        for (var i = 0; i < lista.children.length; i++) {
            lista.children[i].style.display = "block";
        }
    }else{

        for (var i = 0; i < lista.children.length; i++) {

            console.log(lista.children[i].classList.value)

            if(lista.children[i].classList.value === opcionSeleccionada){

                lista.children[i].style.display = "block";
            }else{
                lista.children[i].style.display = "none";
            }

        }
    }

});