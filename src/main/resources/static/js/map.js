let marker, map;

function initMap() {
    // Get the address of the gym from the page
    const address = document.querySelector('.ubication').textContent;
    const defaultPosition = {
        lat: 40.4165,
        lng: -3.7026
    };

    // Create a new Google Map centered on the default position
    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 10,
        center: defaultPosition
    });
    // Add a marker to the map at the default position
    marker = new google.maps.Marker({
        position: defaultPosition,
        map,
        title: "Initial Position"
    });

    // Use the Google Maps Geocoding API to get the latitude and longitude of the address
    geocodeAddress(address);
}

function geocodeAddress(address) {
    const geocoder = new google.maps.Geocoder();
    // Send a geocoding request for the address
    geocoder.geocode({ address: address }, function(results, status) {
        if (status === "OK") {
            // If the geocoding request is successful, get the latitude and longitude of the address
            const nuevaPos = results[0].geometry.location;
            // Set the map center and marker position to the new latitude and longitude
            map.setCenter(nuevaPos);
            marker.setPosition(nuevaPos);
            // Set the zoom level of the map
            map.setZoom(12);
        } else {
            // If the geocoding request fails, log an error message to the console
            console.log("Geocoding failed: " + status);
        }
    });
}
