const baseURL = 'http://localhost:8080/'


document.addEventListener('DOMContentLoaded', () => {

    const zipInput = document.getElementById('zip');
    const searchButton = document.getElementById('form');
    const responseBox = document.getElementById('response');
    const okDate = document.getElementById('okDate');
    const lastFrostDate = document.getElementById('frostDate');
    const infoButton = document.getElementById('infoBTN');
    const informationText = document.getElementById('information');

    infoButton.addEventListener('click', (event) => {
        if (informationText.classList.contains('hidden')) {
            informationText.classList.remove('hidden');
            responseBox.classList.add('hidden');
        } else {
            informationText.classList.add('hidden');
            responseBox.classList.remove('hidden');
        }
        
    })

    searchButton.addEventListener('submit', (event) => {

        const url = baseURL + 'OK2PlantDate/' + zipInput.value;

        event.preventDefault();

        fetch(url).then(response => response.json()).then(data => {
            // responseBox.classList.remove('hidden');
            if (!informationText.classList.contains('hidden')) {
                informationText.classList.add('hidden');
            }
            okDate.innerText = data.ok2PlantDate;
            lastFrostDate.innerText = data.lastFrostDate;

        }).catch((problem) => {
            console.log('There was a problem');
            console.log(problem);
        })
    })

})