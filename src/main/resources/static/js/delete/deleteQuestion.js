// Get all the delete buttons
const btnEliminarList = document.querySelectorAll('.btn-danger');

// Add an event listener to each button
for (const btnEliminar of btnEliminarList) {
    btnEliminar.addEventListener('click', (event) => {
        event.preventDefault(); // Prevent the default behavior of the button

        // Get the ID of the object to be deleted from the "data-id" attribute of the button
        const id = btnEliminar.getAttribute('data-id');

        // Ask the user to confirm the deletion
        const confirmed = confirm('Are you sure you want to delete this Question?');
        if (!confirmed) {
            return; // Exit the function without doing anything
        }

        // Send the DELETE request to the REST API
        fetch(`/api/question?questionId=${encodeURIComponent(id)}`,  {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    // If the object was deleted successfully, update the current page
                    window.location.reload();
                } else {
                    // If the object could not be deleted, show an error message
                    alert('The object could not be deleted.');
                }
            })
            .catch(error => {
                console.error('Error sending DELETE request:', error);
                alert('An error occurred while trying to delete the object.');
            });
    });
}