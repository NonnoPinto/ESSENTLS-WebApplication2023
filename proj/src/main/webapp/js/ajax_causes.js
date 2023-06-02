document.getElementById("ajaxButton")
    .addEventListener("click", searchCause);
console.log("Event listener added to ajaxButton.")

function searchCause() {
    const id = document.getElementById("causeId").value;
    const subCause = document.getElementById("subCause").value;

    url = "/proj-1.0/rest/causes/";

    if (id !== "") {
        console.log("id: " + id);
        url = "/proj-1.0/rest/causes/id/" + id;
        console.log("Request URL: " + url);
    } else {
        console.log("subCause: " + subCause);
        url = "/proj-1.0/rest/causes/srch/" + subCause;
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

    const url = "/proj-1.0/rest/causes/id/" + id;

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

    const url = "/proj-1.0/rest/causes/id/" + id;

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

    //parse the response as JSON and extract the resource-list array
    const resourceList = JSON.parse(xhr.response)["resource-list"];

    div.replaceChildren();

    if (xhr.status !== 200) {
        console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
        console.log(xhr.response);
        div.appendChild(document.createTextNode("Unable to perform the AJAX request."));
        return;
    }
    
    if(resourceList.length > 0){
        div.classList.add("container");
        div.classList.add("justify-content-center", "my-4");
        div.classList.add("col-md-10", "col-lg-8", "col-xl-6");
        div.classList.add("card", "text-center", "border-orange");
        div.classList.add("table-responsive");
    
        const table = document.createElement("table");
        div.appendChild(table);
    
        table.classList.add("table-responsive", "table-hover", "mh-75", "align-middle");
    
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
        eee.classList.add("py-3");
        ee.appendChild(eee); //append the element to the row
    
        eee = document.createElement("th");
        eee.appendChild(document.createTextNode("Name"));
        eee.classList.add("py-3", "px-4");
        ee.appendChild(eee); //append the element to the row
    
        eee = document.createElement("th");
        eee.appendChild(document.createTextNode("Edit"));
        eee.classList.add("py-3", "px-4");
        ee.appendChild(eee); //append the element to the row
    
        eee = document.createElement("th");
        eee.appendChild(document.createTextNode("Delete"));
        eee.classList.add("py-3", "px-4");
        ee.appendChild(eee); //append the element to the row
    
        //table body
        e = document.createElement("tbody");
        table.appendChild(e);

        //iterate over the resource-list array
        for (let i = 0; i < resourceList.length; i++) {
    
            let esncause = resourceList[i].esncause;
            let newname = esncause["name"];
    
            //the row in the table body
            ee = document.createElement("tr");
            e.appendChild(ee); //append the row to the table body
    
            //a generic element of the table body row
            eee = document.createElement("td");
            eee.classList.add("px-4");
            eee.appendChild(document.createTextNode(esncause["id"]));
            ee.appendChild(eee); //append the element to the row
    
            eee = document.createElement("td");
            eee.classList.add("px-4", "min-width-12");
            const nameInput = (document.createElement("input"));
            nameInput.setAttribute("type", "text");
            nameInput.setAttribute("value", esncause["name"]);
            nameInput.setAttribute("id", "nameInput");
            nameInput.classList.add("form-control");
            nameInput.addEventListener("change", function() {newname = nameInput.value; });
            eee.appendChild(nameInput);
            ee.appendChild(eee); //append the element to the row
            //eee.appendChild(document.createTextNode(esncause["name"]));
            //ee.appendChild(eee); //append the element to the row
    
            eee = document.createElement("td");
            eee.classList.add("p-2");
            const editButton = document.createElement("button");
            editButton.appendChild(document.createTextNode("Edit"));
            editButton.classList.add("button", "bg-orange", "text-white", "border-orange", "px-4", "py-2");
            editButton.addEventListener("click", function() {editCause(esncause["id"], newname); });
            eee.appendChild(editButton);
            ee.appendChild(eee); //append the element to the row
    
            eee = document.createElement("td");
            eee.classList.add("p-2");
            const deleteButton = document.createElement("button");
            deleteButton.appendChild(document.createTextNode("Delete"));
            deleteButton.classList.add("button", "bg-orange", "text-white", "border-orange", "px-4", "py-2");
            deleteButton.addEventListener("click", function() {deleteCause(esncause["id"]); });
            eee.appendChild(deleteButton);
            ee.appendChild(eee); //append the element to the row
        }
    }else{
        div.classList.add("text-muted", "text-center");
        div.appendChild(document.createTextNode("No Causes found for the given parameters."));
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

    div.classList.add("text-muted", "text-center");
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

    div.classList.add("text-muted", "text-center");
    div.appendChild(document.createTextNode("Cause " + JSON.parse(xhr.response).esncause["id"] + " deleted successfully."));

    //Creating Alert container
    const alertDiv = document.createElement("div");
    alertDiv.classList.add("alert", "alert-success", "text-center", "fixed-top", "d-flex", "justify-content-center", "align-items-center");
    alertDiv.style.width = "400px";
    alertDiv.style.height = "200px";
    alertDiv.style.backgroundColor = "white";
    alertDiv.style.zIndex = "9999";
    alertDiv.style.border = "5px solid orange";
    alertDiv.style.position = "fixed";
    alertDiv.style.top = "50%";
    alertDiv.style.left = "50%";
    alertDiv.style.transform = "translate(-50%, -50%)";

    const alertText = document.createElement("div");
    alertText.classList.add("alert-text", "text-danger");
    alertText.textContent = "Deleted successfully.";
    alertText.style.fontSize = "25px";

    alertDiv.appendChild(alertText);
    document.body.appendChild(alertDiv);

    // Remove the alert after a certain time (e.g., 3 seconds)
    setTimeout(function() {
        alertDiv.remove();
    }, 3000);
}