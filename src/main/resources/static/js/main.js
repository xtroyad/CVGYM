
// Agregar un evento de clic a cada enlace
loadContent('/../html/homepage.html');

function loadContent(url) {
    fetch(url)
        .then(response => response.text())
        .then(html => {
            const main = document.querySelector("#main");
            main.innerHTML = html;
        })
        .catch(error => console.log(error));
}





