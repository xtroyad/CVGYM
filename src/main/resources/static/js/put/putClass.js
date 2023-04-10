const name = document.querySelector('#name');
const intensity = document.querySelector('#intensity');
const description = document.querySelector('#description');


const myButton = document.getElementById("myButton");

myButton.addEventListener('click', (event) => {

    const courseData = {
        name: name.value,
        intensity: intensity.value,
        description: description.value,

    };


    const courseId = document.querySelector('.form').getAttribute('data-id');

    fetch(`/api/course?courseId=${courseId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(courseData)
    })

        .then(response => {
            if (response.ok) {
                // If the object was deleted successfully, update the current page
                alert('The data has been updated correctly.');
                window.location.href = '/';
            } else {
                // If the object could not be deleted, show an error message
                alert('The object could not be added.');
            }
        })
        .catch(error => {
            console.error('Error sending PUT request:', error);
            alert('An error occurred while trying to delete the object.');
        });
});