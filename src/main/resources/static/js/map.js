let marker, map;

function initMap() {
    // Get the address from the HTML and set the default position to Madrid
    const address = document.querySelector('.ubication').textContent;
    const defaultPosition = {
        lat: 40.4165,
        lng: -3.7026
    };

    // Create a new Google Map and Marker object with the default position
    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 10, // Set the default zoom level to 10
        center: defaultPosition
    });

    marker = new google.maps.Marker({
        position: defaultPosition,
        map,
        title: "Initial Position"
    });

    // Geocode the address to get the latitude and longitude of the location
    geocodeAddress(address);
}

function geocodeAddress(address) {
    const geocoder = new google.maps.Geocoder();

    geocoder.geocode({ address: address }, function(results, status) {
        if (status === "OK") {
            // If the geocoding was successful, set the map center and marker position to the new location
            const nuevaPos = results[0].geometry.location;
            map.setCenter(nuevaPos);
            marker.setPosition(nuevaPos);
            map.setZoom(12); // Set the zoom level to 10
        } else {
            console.log("Geocoding failed: " + status);
        }
    });
}
