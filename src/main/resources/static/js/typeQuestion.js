const typeQuestion = [
    "Comentarios",
    "Más información",
    "Dudas sobre tu contrato",
    "Otros"
];


const select = document.getElementById("filter");

for (let i = 0; i < typeQuestion.length; i++) {
    const option = document.createElement("option");
    option.text = typeQuestion[i];
    select.add(option);
}