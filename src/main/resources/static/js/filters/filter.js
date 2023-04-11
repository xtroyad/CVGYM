const filtro = document.getElementById("filter");

const lista = document.getElementById("list");
// Add an event listener to the filter select element
filtro.addEventListener("change", function () {
    // Get the value of the selected option
    const opSelec = filtro.value;
    // If the selected option is "todos", show all the list items
    if (opSelec === "todos") {
        for (var i = 0; i < lista.children.length; i++) {
            lista.children[i].style.display = "block";
        }
    }else{
        // If the selected option is not "todos", hide all the list items that don't have the selected class
        for (var i = 0; i < lista.children.length; i++) {

            if(lista.children[i].classList.value === opSelec){

                lista.children[i].style.display = "block";
            }else{
                lista.children[i].style.display = "none";
            }

        }
    }
});