// Get all the delete buttons
const dataGymId = document.querySelector('.main');
const btnEliminarList = document.querySelectorAll('.btn-danger');

// Add an event listener to each button
for (const btnEliminar of btnEliminarList) {
    btnEliminar.addEventListener('click', (event) => {
        event.preventDefault(); // Prevent the default behavior of the button

        // Get the ID of the object to be deleted from the "data-id" attribute of the button
        const idGym = dataGymId.getAttribute('data-id');
        const id = btnEliminar.getAttribute('data-id');

        // Send the DELETE request to the REST API
        fetch(`/api/gym-courses?gymId=${encodeURIComponent(idGym)}&courseId=${encodeURIComponent(id)}`,  {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('The data has been updated correctly.');
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