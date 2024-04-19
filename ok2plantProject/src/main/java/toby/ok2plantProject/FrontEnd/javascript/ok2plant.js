const baseURL = 'http://localhost:8080/'


document.addEventListener('DOMContentLoaded', () => {

    const zipInput = document.getElementById('zip');
    const searchButton = document.getElementById('form');
    const responseBox = document.getElementById('response');
    const okDate = document.getElementById('okDate');
    const lastFrostDate = document.getElementById('frostDate');
    const infoButton = document.getElementById('infoBTN');
    const informationText = document.getElementById('information');
    const emailInputBox = document.getElementById('email');
    const checkBox = document.getElementById('follow-up');
    const zipInputDiv = document.getElementById('zipInput');
    const emailInputDiv = document.getElementById('emailInput');
    const responseMessageBox = document.getElementById('message');
    const emailSubmitButton = document.getElementById('emailBTN');
    const submitButton = document.getElementById('btn');
    const emailButtons = document.getElementById('emailButtons');
    const noThanksBTN = document.getElementById('noThanks');

    infoButton.addEventListener('click', (event) => {
        if (responseBox.classList.contains('hidden')) {
            if (informationText.classList.contains('hidden')) {
                informationText.classList.remove('hidden');
            } else {
                informationText.classList.add('hidden');
                if (!okDate.innerText == '') {
                    responseBox.classList.remove('hidden');
                }
            }
        } else {
            informationText.classList.remove('hidden');
            responseBox.classList.add('hidden');
        }
    })

    searchButton.addEventListener('submit', (event) => {

        const url = baseURL + 'OK2PlantDate/' + zipInput.value;

        event.preventDefault();

        fetch(url).then(response => response.json()).then(data => {

            responseBox.classList.remove('hidden');

            if (!informationText.classList.contains('hidden')) {
                informationText.classList.add('hidden');
            }

            okDate.innerText = data.ok2PlantDate;
            lastFrostDate.innerText = data.lastFrostDate;
            responseMessageBox.innerText = data.message;

            zipInputDiv.classList.add('hidden');
            emailInputDiv.classList.remove('hidden');
            submitButton.classList.add('hidden');
            emailButtons.classList.remove('hidden');


        }).catch((problem) => {
            console.log('There was a problem');
            console.log(problem);
        })


        emailSubmitButton.addEventListener('click', event => {

            const url = baseURL + 'EmailRequest';
            const createUserDTO = {
                email: emailInputBox.value,
                ok2PlantDate: okDate.innerText,
                wantsFollowUp: checkBox.checked
            }


            // POST request using fetch()
            fetch(url, {

                // Adding method type
                method: "POST",

                // Adding body or contents to send
                body: JSON.stringify(createUserDTO),

                // Adding headers to the request
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            })

                // Converting to JSON
                .then(response => response.json())

                // Displaying results to console
                .then((data) => {
                    console.log(data);
                    emailInputBox.value = '';
                    checkBox.checked = false;
                })

                emailInputDiv.classList.add('hidden');
                zipInputDiv.classList.remove('hidden');
                emailButtons.classList.add('hidden');
                submitButton.classList.remove('hidden')


        })

        noThanksBTN.addEventListener('click', event => {
            zipInput.value = '';
            emailInputDiv.classList.add('hidden');
            zipInputDiv.classList.remove('hidden');
            emailButtons.classList.add('hidden');
            submitButton.classList.remove('hidden')
        })
    })
})
