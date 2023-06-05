document.getElementById("ajaxButton")
    .addEventListener("click", searchCause);
console.log("Event listener added to ajaxButton.")

document.getElementById("ajaxButtonAll")
    .addEventListener("click", searchCauseAll);
console.log("Event listener added to ajaxButton.")

var searchAll = false;
console.log(searchAll);

function searchCauseAll(){
    searchAll = true;
    searchCause();
    searchAll = false;
}

function searchCause() {
    var id = document.getElementById("causeId").value;
    var subCause = document.getElementById("subCause").value;

    if (searchAll){
        id = "";
        subCause = "";
    }

    url = contextPath+"/rest/causes/";

    if (id !== "") {
        console.log("id: " + id);
        url = contextPath+"/rest/causes/id/" + id;
        console.log("Request URL: " + url);
    } else {
        console.log("subCause: " + subCause);
        url = contextPath+"/rest/causes/srch/" + subCause;
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

    const url = contextPath+"/rest/causes/id/" + id;

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

    // Creating the confirmation popup
    const cardContainer = document.createElement("div");
    cardContainer.classList.add("card", "text-center");
    cardContainer.style.width = "400px";
    cardContainer.style.position = "fixed";
    cardContainer.style.top = "50%";
    cardContainer.style.left = "50%";
    cardContainer.style.transform = "translate(-50%, -50%)";

    const cardBody = document.createElement("div");
    cardBody.classList.add("card-body");

    const confirmationMessage = document.createElement("h5");
    confirmationMessage.classList.add("card-title");
    confirmationMessage.textContent = "Are you sure you want to delete this cause?";
    cardBody.appendChild(confirmationMessage);

    const deleteButton = document.createElement("button");
    deleteButton.classList.add("button", "btn", "button-danger", "mx-2", "px-4", "py-2");
    deleteButton.textContent = "Yes";
    deleteButton.addEventListener("click", () => {
        // Close the popup
        cardContainer.remove();

        // Perform the delete action here
        performDelete(id);
    });
    cardBody.appendChild(deleteButton);

    const cancelButton = document.createElement("button");
    cancelButton.classList.add("button", "btn", "btn-secondary", "mx-2", "px-4", "py-2");
    cancelButton.textContent = "No";
    cancelButton.addEventListener("click", () => {
        // Close the popup
        cardContainer.remove();
    });
    cardBody.appendChild(cancelButton);

    cardContainer.appendChild(cardBody);
    document.body.appendChild(cardContainer);
}

function performDelete(id) {
    const url = contextPath+"/rest/causes/id/" + id;

    console.log("Request URL: " + url);

    const xhr = new XMLHttpRequest();

    if (!xhr) {
        alert("Cannot create an XMLHTTP instance.");
        alert("Giving up :( Cannot create an XMLHttpRequest instance");
        return false;
    }

    xhr.onreadystatechange = function() {
        processDeleteResponse(this);
    };

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
    const instructions = document.getElementById("instructions");

    //parse the response as JSON and extract the resource-list array
    const resourceList = JSON.parse(xhr.response)["resource-list"];

    div.replaceChildren();
    instructions.replaceChildren();

    if (xhr.status !== 200) {
        console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
        console.log(xhr.response);
        div.appendChild(document.createTextNode("Unable to perform the AJAX request."));
        return;
    }
    
    if(resourceList.length > 0){
        const instructionsText = document.createElement("div");
        instructions.appendChild(instructionsText);

        instructions.classList.add("justify-content-center", "mt-2");
        instructions.classList.add("col-md-10", "col-lg-8", "col-xl-6");

        instructionsText.classList.add("alert", "alert-info");
        instructionsText.setAttribute("role", "alert");
        instructionsText.classList.add("text-center");
        instructionsText.innerHTML = "Edit cause name: modify the input field and click the edit button to confirm.";

        div.classList.add("container");
        div.classList.add("justify-content-center", "my-2");
        div.classList.add("col-md-10", "col-lg-8", "col-xl-6");
        div.classList.add("card", "text-center", "border-green");
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
            editButton.classList.add("button", "bg-green", "text-white", "border-green", "px-4", "py-2");
            editButton.addEventListener("click", function() {editCause(esncause["id"], newname); });
            eee.appendChild(editButton);
            ee.appendChild(eee); //append the element to the row
    
            eee = document.createElement("td");
            eee.classList.add("p-2");
            const deleteButton = document.createElement("button");
            deleteButton.appendChild(document.createTextNode("Delete"));
            deleteButton.classList.add("button", "bg-white", "color-green", "border-green", "px-4", "py-2");
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
    const instructions = document.getElementById("instructions");

    div.replaceChildren();
    instructions.replaceChildren();

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
    const instructions = document.getElementById("instructions");

    div.replaceChildren();
    instructions.replaceChildren();

    if (xhr.status !== 200) {
        console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
        console.log(xhr.response);
        div.appendChild(document.createTextNode("Unable to perform the AJAX request."));
        return;
    }

    div.classList.add("text-muted", "text-center");
    div.appendChild(document.createTextNode("Cause " + JSON.parse(xhr.response).esncause["id"] + " deleted successfully."));


    // Create Deleted Successfully popup alert
    const modalContainer = document.createElement("div");
    modalContainer.classList.add("modal", "fade");
    modalContainer.id = "deleteModal";

    const modalDialog = document.createElement("div");
    modalDialog.classList.add("modal-dialog", "modal-dialog-centered");
    modalContainer.appendChild(modalDialog);

    const modalContent = document.createElement("div");
    modalContent.classList.add("modal-content");
    modalDialog.appendChild(modalContent);

    const modalHeader = document.createElement("div");
    modalHeader.classList.add("modal-header");

    const modalTitle = document.createElement("h5");
    modalTitle.classList.add("modal-title");
    modalTitle.textContent = "Deleted Successfully";
    modalHeader.appendChild(modalTitle);

    modalContent.appendChild(modalHeader);

    const modalBody = document.createElement("div");
    modalBody.classList.add("modal-body");
    modalBody.textContent =
        "Cause " + JSON.parse(xhr.response).esncause["id"] + " deleted successfully.";
    modalContent.appendChild(modalBody);

    div.appendChild(modalContainer);

    $(modalContainer).modal("show");

    // Remove the Bootstrap modal after 2.5 seconds
    setTimeout(function () {
        $(modalContainer).modal("hide");
        modalContainer.remove();
    }, 2500);
}