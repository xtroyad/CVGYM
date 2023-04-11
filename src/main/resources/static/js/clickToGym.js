const elementToClickList =  document.querySelectorAll('li');
// Add a click event listener to each list item
for( const elementToClick of elementToClickList){
    elementToClick.addEventListener("click", (event)=>{
        // Get the clicked element
        const target = event.target;
        // If the clicked element is a button, return and do nothing
        if (target.classList.contains('btn-secondary') || target.classList.contains('btn-danger')) {
            return;
        }
        // Get the id of the clicked gym from the data-id attribute of the list item
        const gymId = elementToClick.getAttribute('data-id');
        // Redirect to the page for the clicked gym
        window.location.href = `/center?gymId=${gymId}`;
    });
}
