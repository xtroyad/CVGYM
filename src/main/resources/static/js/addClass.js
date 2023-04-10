// Get the confirmation button
const btnConfirmar = document.querySelector('.btn-light');

// Add an event listener to the button
btnConfirmar.addEventListener('click', (event) => {
    event.preventDefault(); // Prevent the default behavior of the button

    // Get the ID of the object to be added from the "data-id" attribute of the button
    const selectGym = document.getElementById("mySelectGym");
    const gymId = selectGym.value;
    const selectCourse = document.getElementById("mySelectCourse");
    const courseId = selectCourse.value;
    //const id = btnConfirmar.getAttribute('data-id');

    // Send the POST request to the REST API
    fetch(`/api/gym-courses?courseId=${courseId}&gymId=${gymId}`,  {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                // If the object was deleted successfully, update the current page
                alert('Los datos se han actualizado correctamente.');
                window.location.href = '/';
            } else {
                // If the object could not be deleted, show an error message
                alert('The object could not be added.');
            }
        })
        .catch(error => {
            console.error('Error sending POST request:', error);
            alert('An error occurred while trying to delete the object.');
        });
});





