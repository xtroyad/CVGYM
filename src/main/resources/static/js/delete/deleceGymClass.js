// Get all the delete buttons
const btnEliminarList = document.querySelectorAll('.btn-danger');

// Add an event listener to each button
for (const btnEliminar of btnEliminarList) {
    btnEliminar.addEventListener('click', (event) => {
        event.preventDefault();

        // Get the id of the course to delete from the data-id attribute of the button
        const id = btnEliminar.getAttribute('data-id');

        // Send a DELETE request to the REST API to delete the course with the specified id
        fetch(`/api/course?courseId=${encodeURIComponent(id)}`,  {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('The data has been updated correctly.');
                    // If the response is ok, show an alert and reload the page
                    window.location.reload();
                } else {
                    // If the object could not be deleted, show an error message
                    alert('The object could not be deleted.');
                }
            })
            .catch(error => {
                // If there is an error, log it and show an alert with an error message
                console.error('Error sending DELETE request:', error);
                alert('An error occurred while trying to delete the object.');
            });
    });
}