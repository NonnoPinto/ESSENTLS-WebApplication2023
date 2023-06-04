var submitButton = document.getElementById("ajaxButton");
document.addEventListener("keypress", function(event) {
    if (event.key === "Enter") {
        console.log("try");
        event.preventDefault();
        submitButton.click();
    }
});