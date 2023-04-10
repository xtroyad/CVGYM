const btnConfirmar = document.querySelector('.btn-light');
btnConfirmar.addEventListener('click', (event) => {
    event.preventDefault(); // Prevent the default behavior of the button

    // Get the values from the form inputs
    const name = document.getElementById("name").value;
    const lastName = document.getElementById("lastName").value;
    const selectGym = document.getElementById("selectGym");
    const gymId = selectGym.value;

    // Create the coach object
    const coach = {
        name: name,
        lastName: lastName
    };

    // Send the POST request to the REST API
    fetch(`/api/coach?gymId=${gymId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(coach)
    })
        .then(response => {
            if (response.ok) {
                // If the object was added successfully, update the current page
                alert('The data has been updated successfully.');
                window.location.href = '/';
            } else {
                // If the object could not be added, show an error message
                alert('The object could not be added.');
            }
        })
        .catch(error => {
            console.error('Error sending POST request:', error);
            alert('An error occurred while trying to add the object.');
        });
});
