function validateDates() {
    var startDate = document.getElementById("eventStart").value;
    var endDate = document.getElementById("eventEnd").value;

    var subscriptionStart = document.getElementById("subscriptionStart").value;
    var subscriptionEnd = document.getElementById("subscriptionEnd").value;
    var withdrawalDate = document.getElementById("withdrawalEnd").value;

    if (startDate > endDate) {
        alert("The event start date must be before the end date.");
        document.getElementById("eventEnd").value = startDate;
        return false;
    }
    if (subscriptionStart > subscriptionEnd) {
        alert("The event subscription start date must be before the end date.");
        document.getElementById("subscriptionEnd").value = subscriptionStart;
        return false;
    }
    if (endDate < withdrawalDate) {
        alert("The event end date must be after the withdrawal date.");
        document.getElementById("withdrawalEnd").value = endDate;
        return false;
    }

    return true;
}