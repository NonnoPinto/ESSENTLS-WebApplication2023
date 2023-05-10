/*
Author: Andrea Campagnol (andrea.campagnol.1@studenti.unipd.it)
Version: 1.0
Since: 1.0
*/

//import { BASE_URL } from "constants";
const URL = "http://localhost:8080/proj-1.0/login";

// Add an event listener to the button,
// to invoke the function making the AJAX call
document.getElementById("loginButton")
    .addEventListener("click", loginRequest);
console.log("Event listener added to loginButton.")

/**
 * Try to login the user using POST parameters passe dby the login form.
 *
 * @returns {boolean} true if the HTTP request was successful; false otherwise.
 */
function loginRequest(){
    // get the value of the email from the form field
    const email = document.getElementById("email").value;

    // get the value of the password from the form field
    const password = document.getElementById("password").value;

    //Url to send the request
    const url = URL;

    // the XMLHttpRequest object
    const xhr = new XMLHttpRequest();

    if (!xhr) {
        console.log("Cannot create an XMLHttpRequest instance.")

        alert("Giving up :( Cannot create an XMLHttpRequest instance");
        return false;
    }

    // set up the call back for handling the request
    xhr.onreadystatechange = function () {
        processResponse(this);
    };

    // perform the request
    console.log("Performing the HTTP POST request.");

    var params = "email="+email+"&password="+password;

    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    console.log("try !!!!!");
    xhr.send(params);

    console.log("HTTP POST request sent.");
}

/**
 * Processes the HTTP response. Writes an error message back to the jsp page if an error occurs during the login.
 *
 * @param xhr the XMLHttpRequest object performing the request.
 */
function processResponse(xhr){

    // not finished yet
    if (xhr.readyState !== XMLHttpRequest.DONE) {
        console.log("Request state: %d. [0 = UNSENT; 1 = OPENED; 2 = HEADERS_RECEIVED; 3 = LOADING]",
            xhr.readyState);
        return;
    }

    const errorMessageDiv = document.getElementById("errorMessageDiv");
    //const messageText = document.getElementById("messageText");

    if (xhr.status !== 200) {
        console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
        console.log(xhr.response);

        const message = JSON.parse(xhr.responseText)["message"];

        console.log(message.message);

        /*
        <div class="alert alert-danger" role="alert">
										<p id="messageText"></p>
									</div>
                                    */
        errorMessageDiv.replaceChildren();
        var messageDiv = document.createElement("div");
        messageDiv.className = "alert alert-danger";
        messageDiv.role = "alert";
        var messageText = document.createElement("p");
        messageText.innerHTML = message.message;
        messageDiv.appendChild(messageText);
        errorMessageDiv.appendChild(messageDiv);
        errorMessageDiv.style.visibility = 'visible';
        errorMessageDiv.classList.add("error-animation");
        //errorMessageDiv.effect("shake", {times:1}, 500);
        setTimeout(function(){
            errorMessageDiv.classList.remove("error-animation");
        },500);

        //errorMessageDiv.appendChild(document.createTextNode("Unable to perform the AJAX request."));

        return;
    }

    const message = JSON.parse(xhr.responseText)["message"];
    window.location.replace(message.message);


}