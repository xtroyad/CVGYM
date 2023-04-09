const address = document.querySelector('#address');
const number = document.querySelector('#number');
const zip = document.querySelector('#zip');
const ca = document.querySelector('#ccaa');
const city = document.querySelector('#city');
const phoneNumber = document.querySelector('#phoneNumber');
const name = document.querySelector('#name');
const lastName = document.querySelector('#lastName');

const myButton = document.getElementById("myButton");

myButton.addEventListener('click', (event) => {

    const gymData = {
        address: address.value,
        number: number.value,
        zip: zip.value,
        ccaa: ca.value,
        city: city.value,
        phoneNumber: phoneNumber.value,
    };

    const managerData = {
        name: name.value,
        lastName: lastName.value
    };

    const gymId = document.querySelector('.form').getAttribute('data-id');

    fetch(`/api/gym?gymId=${gymId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(gymData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar los datos del gimnasio');
            }
            const managerClass = document.querySelector('.managerClass');
            const managerId =managerClass.getAttribute('data-id');
            return fetch(`/api/manager?managerId=${managerId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(managerData)
            });
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar los datos del gerente');
            } else {
                alert('Los datos se han actualizado correctamente.');
                window.location.href = '/centers/';
            }
        })
        .catch(error => {
            console.error('Error al actualizar los datos:', error);
            alert('Ocurrió un error al intentar actualizar los datos.');
        });
});
