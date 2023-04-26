document.getElementById("ajaxButton")
    .addEventListener("click", searchTag);
console.log("Event listener added to ajaxButton.")

function searchTag() {
    const subTag = document.getElementById("subTag").value;

    console.log("subTag: " + subTag);

    const url = "http://localhost:8080/proj-1.0/rest/tags/" + subTag;

    console.log("Request URL: " + url);

    const xhr = new XMLHttpRequest();

    if (!xhr) {
        alert("Cannot create an XMLHTTP instance.");

        alert("Giving up :( Cannot create an XMLHttpRequest instance");
        return false;
    }

    xhr.onreadystatechange = function() {
        processGetResponse(this);
    }

    console.log("Performing GET request to URL: " + url);
    xhr.open("GET", url);
    xhr.send();

    console.log("Request sent. Waiting for response...");
}

function deleteTag(name) {

    console.log("Deleting tag: " + name);

    const url = "http://localhost:8080/proj-1.0/rest/tags/" + name;

    console.log("Request URL: " + url);

    const xhr = new XMLHttpRequest();

    if (!xhr) {
        alert("Cannot create an XMLHTTP instance.");

        alert("Giving up :( Cannot create an XMLHttpRequest instance");
        return false;
    }

    xhr.onreadystatechange = function() {
        processDeleteResponse(this);
    }

    console.log("Performing DELETE request to URL: " + url);
    xhr.open("DELETE", url);
    xhr.send();

    console.log("Request sent. Waiting for response...");
}

function processGetResponse(xhr) {

    if(xhr.readyState !== XMLHttpRequest.DONE) {
        console.log("Request state: %d. [0 = UNSENT; 1 = OPENED; 2 = HEADERS_RECEIVED; 3 = LOADING]",
            xhr.readyState);
        return;
    }

    const div = document.getElementById("results");

    div.replaceChildren();

    if (xhr.status !== 200) {
        console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
        console.log(xhr.response);
        div.appendChild(document.createTextNode("Unable to perform the AJAX request."));
        return;
    }

    const table = document.createElement("table");
    div.appendChild(table);

    let e, ee, eee;


    // table header
    e = document.createElement("thead");
    table.appendChild(e); // append the table header to the table

    // the row in the table header
    ee = document.createElement("tr");
    e.appendChild(ee); // append the row to the table header

    // a generic element of the table header row
    eee = document.createElement("th");
    eee.appendChild(document.createTextNode("Tag"));
    ee.appendChild(eee); // append the cell to the row

    eee = document.createElement("th");
    eee.appendChild(document.createTextNode("Delete"));
    ee.appendChild(eee); // append the cell to the row

    // table body
    e = document.createElement("tbody");
    table.appendChild(e); // append the table body to the table

    //parse the response as JSON and extract the resource-list array
    const resourceList = JSON.parse(xhr.responseText)["resource-list"];

    for (let i = 0; i < resourceList.length; ++i) {

        let esntag = resourceList[i].esntag;

        // the row in the table body
        ee = document.createElement("tr");
        e.appendChild(ee); // append the row to the table body

        // a generic element of the table body row
        eee = document.createElement("td");
        eee.appendChild(document.createTextNode(esntag["name"]));
        ee.appendChild(eee); // append the cell to the row

        eee = document.createElement("td");
        const button = document.createElement("button");
        button.appendChild(document.createTextNode("Delete"));
        button.addEventListener("click", function() { deleteTag(esntag["name"]); });
        eee.appendChild(button);
        ee.appendChild(eee); // append the cell to the row
    }
}

function processDeleteResponse(xhr) {

    if(xhr.readyState !== XMLHttpRequest.DONE) {
        console.log("Request state: %d. [0 = UNSENT; 1 = OPENED; 2 = HEADERS_RECEIVED; 3 = LOADING]",
            xhr.readyState);
        return;
    }

    const div = document.getElementById("results");

    div.replaceChildren();

    if (xhr.status !== 200) {
        console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
        console.log(xhr.response);
        div.appendChild(document.createTextNode("Unable to perform the AJAX request."));
        return;
    }

    div.appendChild(document.createTextNode("Tag " + JSON.parse(xhr.response).esntag["name"] + " deleted successfully."));
}