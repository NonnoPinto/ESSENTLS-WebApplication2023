document.getElementById("ajaxButton")
    .addEventListener("click", searchCause);
console.log("Event listener added to ajaxButton.")

function searchCause() {
    const id = document.getElementById("causeId").value;
    const subCause = document.getElementById("subCause").value;

    url = "http://localhost:8080/proj-1.0/rest/causes/";

    if (id !== "") {
        console.log("id: " + id);
        url = "http://localhost:8080/proj-1.0/rest/causes/id/" + id;
        console.log("Request URL: " + url);
    } else {
        console.log("subCause: " + subCause);
        url = "http://localhost:8080/proj-1.0/rest/causes/srch/" + subCause;
        console.log("Request URL: " + url);
    }

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

function editCause(id, name) {

    console.log("Editing cause: " + id);

    const url = "http://localhost:8080/proj-1.0/rest/causes/id/" + id;

    console.log("Request URL: " + url);

    const xhr = new XMLHttpRequest();

    if (!xhr) {
        alert("Cannot create an XMLHTTP instance.");

        alert("Giving up :( Cannot create an XMLHttpRequest instance");
        return false;
    }

    xhr.onreadystatechange = function() {
        processPutResponse(this);
    }

    console.log("Performing PUT request to URL: " + url);
    xhr.open("PUT", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify({esncause: {id: id, name: name}}));

}

function deleteCause(id) {

    console.log("Deleting cause: " + id);

    const url = "http://localhost:8080/proj-1.0/rest/causes/id/" + id;

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

    //table header
    e = document.createElement("thead");
    table.appendChild(e);

    //the row in the table header
    ee = document.createElement("tr");
    e.appendChild(ee);

    //a generic element of the table header row
    eee = document.createElement("th");
    eee.appendChild(document.createTextNode("Cause ID"));
    ee.appendChild(eee); //append the element to the row

    eee = document.createElement("th");
    eee.appendChild(document.createTextNode("Name"));
    ee.appendChild(eee); //append the element to the row

    eee = document.createElement("th");
    eee.appendChild(document.createTextNode("Edit"));
    ee.appendChild(eee); //append the element to the row

    eee = document.createElement("th");
    eee.appendChild(document.createTextNode("Delete"));
    ee.appendChild(eee); //append the element to the row

    //table body
    e = document.createElement("tbody");
    table.appendChild(e);

    //parse the response as JSON and extract the resource-list array
    const resourceList = JSON.parse(xhr.response)["resource-list"];

    //iterate over the resource-list array
    for (let i = 0; i < resourceList.length; i++) {

        let esncause = resourceList[i].esncause;
        let newname = esncause["name"];

        //the row in the table body
        ee = document.createElement("tr");
        e.appendChild(ee); //append the row to the table body

        //a generic element of the table body row
        eee = document.createElement("td");
        eee.appendChild(document.createTextNode(esncause["id"]));
        ee.appendChild(eee); //append the element to the row

        eee = document.createElement("td");
        const nameInput = (document.createElement("input"));
        nameInput.setAttribute("type", "text");
        nameInput.setAttribute("value", esncause["name"]);
        nameInput.setAttribute("id", "nameInput");
        nameInput.addEventListener("change", function() {newname = nameInput.value; });
        eee.appendChild(nameInput);
        ee.appendChild(eee); //append the element to the row
        //eee.appendChild(document.createTextNode(esncause["name"]));
        //ee.appendChild(eee); //append the element to the row

        eee = document.createElement("td");
        const editButton = document.createElement("button");
        editButton.appendChild(document.createTextNode("Edit"));
        editButton.addEventListener("click", function() {editCause(esncause["id"], newname); });
        eee.appendChild(editButton);
        ee.appendChild(eee); //append the element to the row

        eee = document.createElement("td");
        const deleteButton = document.createElement("button");
        deleteButton.appendChild(document.createTextNode("Delete"));
        deleteButton.addEventListener("click", function() {deleteCause(esncause["id"]); });
        eee.appendChild(deleteButton);
        ee.appendChild(eee); //append the element to the row
    }
}

function processPutResponse(xhr) {

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

    div.appendChild(document.createTextNode("Cause { id: " + JSON.parse(xhr.response).esncause["id"] + ", name: " + JSON.parse(xhr.response).esncause["name"] + " } updated successfully."));
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

    div.appendChild(document.createTextNode("Cause " + JSON.parse(xhr.response).esncause["id"] + " deleted successfully."));
}