// Get the input fields from the HTML form
const address = document.querySelector('#address');
const number = document.querySelector('#number');
const zip = document.querySelector('#zip');
const ca = document.querySelector('#ccaa');
const city = document.querySelector('#city');
const phoneNumber = document.querySelector('#phoneNumber');
const name = document.querySelector('#name');
const lastName = document.querySelector('#lastName');
// Get the button element from the HTML form
const myButton = document.getElementById("myButton");
// Add a click event listener to the button
myButton.addEventListener('click', (event) => {
    // Collect the gym data from the input fields
    const gymData = {
        address: address.value,
        number: number.value,
        zip: zip.value,
        ccaa: ca.value,
        city: city.value,
        phoneNumber: phoneNumber.value,
    };
    // Collect the manager data from the input fields
    const managerData = {
        name: name.value,
        lastName: lastName.value
    };
    // Get the gym ID from the HTML form
    const gymId = document.querySelector('.form').getAttribute('data-id');
    // Send a PUT request to update the gym data
    fetch(`/api/gym?gymId=${gymId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(gymData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error updating gym data');
            }
            // Get the manager ID from the HTML form
            const managerClass = document.querySelector('.managerClass');
            const managerId =managerClass.getAttribute('data-id');
            // Determine whether to send a PUT or POST request to update the manager data
            let managerMethod = 'PUT';
            let managerEndpoint = `/api/manager?managerId=${managerId}`;
            if (managerId === '0') {
                managerMethod = 'POST';
                managerEndpoint =  `/api/manager?gymId=${gymId}`;
            }
            // Send a PUT or POST request to update the manager data
            return fetch(managerEndpoint, {
                method: managerMethod,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(managerData)
            });
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error updating manager data');
            } else {
                alert('The data has been updated successfully.');
                window.location.href = '/centers/';
            }
        })
        .catch(error => {
            console.error('Error updating data:', error);
            alert('An error occurred while trying to update the data.');
        });
});