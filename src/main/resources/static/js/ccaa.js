const ccaa = [
    "Andalucía",
    "Aragón",
    "Asturias",
    "Baleares",
    "Canarias",
    "Cantabria",
    "Castilla-La Mancha",
    "Castilla y León",
    "Cataluña",
    "Comunidad de Madrid",
    "Comunidad Valenciana",
    "Extremadura",
    "Galicia",
    "La Rioja",
    "Murcia",
    "Navarra",
    "País Vasco"
];

// Get the select element with the ID "ccaa"
const select = document.getElementById("ccaa");
// Add an option element for each item in the "ccaa" array
for (let i = 0; i < ccaa.length; i++) {
    const option = document.createElement("option");
    option.text = ccaa[i];
    select.add(option);
}