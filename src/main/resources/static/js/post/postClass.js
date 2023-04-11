
const btnConfirmar = document.querySelector('.btn-light');

// Add an event listener to the confirmation butto
btnConfirmar.addEventListener('click', (event) => {
    event.preventDefault();

    // Get the values of the selected gym and course from their respective select elements
    const selectGym = document.getElementById("mySelectGym");
    const gymId = selectGym.value;
    const selectCourse = document.getElementById("mySelectCourse");
    const courseId = selectCourse.value;
    // Send a POST request to the REST API to add the selected course to the selected gym
    fetch(`/api/gym-courses?courseId=${courseId}&gymId=${gymId}`,  {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                // If the response is ok, show an alert and redirect to the homepage
                alert('The data has been updated correctly.');
                window.location.href = '/';
            } else {
                // If the response is not ok, show an alert with an error message
                alert('The object could not be added.');
            }
        })
        .catch(error => {
            // If there is an error, log it and show an alert with an error message
            console.error('Error sending POST request:', error);
            alert('An error occurred while trying to delete the object.');
        });
});





