document.getElementById("ajaxButton")
    .addEventListener("click", searchTag);
console.log("Event listener added to ajaxButton.")

document.getElementById("ajaxButtonAll")
    .addEventListener("click", searchTagAll);
console.log("Event listener added to ajaxButton.")

var searchAll = false;
console.log(searchAll);

function searchTagAll(){
    searchAll = true;
    searchTag();
    searchAll = false;
}

function searchTag() {
    var subTag = document.getElementById("subTag").value;

    if (searchAll){
        subTag = "";
    }

    console.log("subTag: " + subTag);

    const url = "/proj-1.0/rest/tags/" + subTag;

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
    confirmationMessage.textContent = "Are you sure you want to delete this tag?";
    cardBody.appendChild(confirmationMessage);

    const deleteButton = document.createElement("button");
    deleteButton.classList.add("btn", "btn-danger", "mx-2");
    deleteButton.textContent = "Yes";
    deleteButton.addEventListener("click", () => {
        // Close the popup
        cardContainer.remove();

        // Perform the delete action here
        performDeleteTag(name);
    });
    cardBody.appendChild(deleteButton);

    const cancelButton = document.createElement("button");
    cancelButton.classList.add("btn", "btn-secondary", "mx-2");
    cancelButton.textContent = "No";
    cancelButton.addEventListener("click", () => {
        // Close the popup
        cardContainer.remove();
    });
    cardBody.appendChild(cancelButton);

    cardContainer.appendChild(cardBody);
    document.body.appendChild(cardContainer);
}

function performDeleteTag(name) {
    const url = "/proj-1.0/rest/tags/" + name;

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

    //parse the response as JSON and extract the resource-list array
    const resourceList = JSON.parse(xhr.responseText)["resource-list"];

    div.replaceChildren();

    if (xhr.status !== 200) {
        console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
        console.log(xhr.response);
        div.appendChild(document.createTextNode("Unable to perform the AJAX request."));
        return;
    }

    if(resourceList.length > 0) {
        div.classList.add("container");
        div.classList.add("justify-content-center", "my-4");
        div.classList.add("col-md-10", "col-lg-8", "col-xl-6");
        div.classList.add("card", "text-center", "border-cyan");
        div.classList.add("table-responsive");

        const table = document.createElement("table");
        div.appendChild(table);

        table.classList.add("table-responsive", "table-hover", "mh-75", "align-middle");

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
        eee.classList.add("py-3");
        ee.appendChild(eee); // append the cell to the row

        eee = document.createElement("th");
        eee.appendChild(document.createTextNode("Delete"));
        eee.classList.add("py-3", "px-4");
        ee.appendChild(eee); // append the cell to the row

        // table body
        e = document.createElement("tbody");
        table.appendChild(e); // append the table body to the table


        for (let i = 0; i < resourceList.length; ++i) {

            let esntag = resourceList[i].esntag;

            // the row in the table body
            ee = document.createElement("tr");
            e.appendChild(ee); // append the row to the table body

            // a generic element of the table body row
            eee = document.createElement("td");
            eee.classList.add("px-4");
            eee.appendChild(document.createTextNode(esntag["name"]));
            ee.appendChild(eee); // append the cell to the row

            eee = document.createElement("td");
            eee.classList.add("p-2");
            const button = document.createElement("button");
            button.appendChild(document.createTextNode("Delete"));
            button.classList.add("button", "bg-cyan", "text-white", "border-cyan", "px-4", "py-2");
            button.addEventListener("click", function () {
                deleteTag(esntag["name"]);
            });
            eee.appendChild(button);
            ee.appendChild(eee); // append the cell to the row
        }
    } else{
        div.classList.add("text-muted", "text-center");
        div.appendChild(document.createTextNode("No Tags found for the given parameters"));
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