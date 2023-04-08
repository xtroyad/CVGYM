let marker, map;

function initMap() {

    const direccion = document.querySelector('.ubicacion').textContent;
    const posicion = {
        lat: -25.363,
        lng: 131.044
    }

    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 4,
        center: posicion
    })

    marker = new google.maps.Marker({
        position: posicion,
        map,
        title: "Posición Inicial"
    })

    geocodeAddress(direccion)
}

function geocodeAddress(direccion) {
    const geocoder = new google.maps.Geocoder();

    geocoder.geocode({ address: direccion }, function(results, status) {
        if (status === "OK") {
            // Centrar el mapa en la ubicación de la dirección
            const nuevaPos = results[0].geometry.location;
            map.setCenter(nuevaPos);
            marker.setPosition(nuevaPos);
        } else {
            console.log("Geocodificación fallida: " + status);
        }
    });
}







function centraMapa(position) {
    const nuevaPos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
    }
    marker.setPosition(nuevaPos)
    map.setCenter(nuevaPos)
}

function onError(error) {
    console.log("Se ha producido un error y lo hemos gestionado")
    console.error(error)
}