const elementToClickList =  document.querySelectorAll('li');

for( const elementToClick of elementToClickList){
    elementToClick.addEventListener("click", (event)=>{
        const target = event.target;
        if (target.classList.contains('btn-secondary') || target.classList.contains('btn-danger')) {
            return; // evita que se ejecute el cÃ³digo existente de los botones "Editar" y "Eliminar"
        }

        const gymId = elementToClick.getAttribute('data-id');

        window.location.href = `/center?gymId=${gymId}`;
    });
}
