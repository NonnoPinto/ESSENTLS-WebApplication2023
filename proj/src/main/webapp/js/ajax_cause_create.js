document.getElementById("ajaxButton")
    .addEventListener("click", createCause);
console.log("Event listener added to ajaxButton.")

function createCause() {
    const name = document.getElementById("causeName").value;

    const url = "http://localhost:8080/proj-1.0/rest/causes/";

    console.log("Request URL: " + url);

    const xhr = new XMLHttpRequest();

    if (!xhr) {
        alert("Cannot create an XMLHTTP instance.");

        alert("Giving up :( Cannot create an XMLHttpRequest instance");
        return false;
    }

    xhr.onreadystatechange = function() {
        processPostResponse(this);
    }

    console.log("Performing POST request to URL: " + url);
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify({esncause: {id: 1, name: name}}));

    console.log("Request sent. Waiting for response...");
}

function processPostResponse(xhr) {
    if(xhr.readyState !== XMLHttpRequest.DONE) {
        console.log("Request state: %d. [0 = UNSENT; 1 = OPENED; 2 = HEADERS_RECEIVED; 3 = LOADING]",
            xhr.readyState);
        return;
    }

    const div = document.getElementById("results");

    div.replaceChildren();

    if (xhr.status !== 201) {
        console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
        console.log(xhr.response);
        div.appendChild(document.createTextNode("Unable to perform the AJAX request."));
        return;
    }

    div.appendChild(document.createTextNode("Cause { id: " + JSON.parse(xhr.response).esncause["id"] + ", name: " + JSON.parse(xhr.response).esncause["name"] + " } created successfully."));
}