<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19/4/2023
  Time: 12:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payments</title>
</head>
<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>

<c:choose>
    <c:when test="${action.equals(\"event\")}">
        <h1>Event Payment</h1>
        <p>
            You are paying: &euro; ${event.price}
        </p>
    </c:when>
    <c:when test="${action.equals(\"sub\")}">
        <h1>Association Payment</h1>
        <p>
            You are paying: &euro; ${subPrice}
        </p>
    </c:when>
    <c:otherwise>
        <p>Something did not work :(</p>
    </c:otherwise>
</c:choose>

<a href="joinEvent?id=${event.id}"><button>Payed</button></a>
<!-- Set up a container element for the button -->
<div id="paypal-button-container"></div>

<!-- Include the PayPal JavaScript SDK -->
<script src="https://www.paypal.com/sdk/js?client-id=sb&currency=EUR"></script>

<script>
    // Render the PayPal button into #paypal-button-container
    paypal.Buttons({

        // Call your server to set up the transaction
        createOrder: function(data, actions) {
            return actions.order.create({
                purchase_units: [{
                    amount: {
                        value: '${subPrice}'
                    }
                }]
            });
        },

        // Call your server to finalize the transaction
        onApprove: function(data, actions) {
            return actions.order.capture().then(function(orderData) {
                // Successful capture! For demo purposes:
                console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));
                let transaction = orderData.purchase_units[0].payments.captures[0];
                alert('Transaction '+ transaction.status + ': ' + transaction.id + '\n\nSee console for all available details');
            });
        }

    }).render('#paypal-button-container');
</script>
</body>
</html>
