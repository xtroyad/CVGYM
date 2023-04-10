const filtro = document.getElementById("filter");

const lista = document.getElementById("list");

filtro.addEventListener("change", function () {
    const opcionSeleccionada = filtro.value;

    console.log( opcionSeleccionada )

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