<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19/4/2023
  Time: 12:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../html/cdn.html" %>
<html>
<head>
	<meta name="description" content="ESN Padova application">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Payments</title>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="container">
	<div class="row justify-content-center my-4">
		<div class="col-xxl-10 col-12">
			<div class="card">
				<div class="card-body text-center">
					<c:choose>
						<c:when test="${action.equals(\"event\")}">
							<h1>Event Payment</h1>
						</c:when>
						<c:when test="${action.equals(\"sub\")}">
							<h1>Association Payment</h1>
						</c:when>
						<c:otherwise>
							<p>Something did not work :(</p>
						</c:otherwise>
					</c:choose>
					<hr>
					<div>
						<c:choose>
							<c:when test="${action.equals(\"event\")}">
								<p>
									You are paying: &euro; ${event.price}.
									Choose a payment method:
									<a href="add_payment?action=${action}&id=${event.id}">
										<button>Payed</button>
									</a>
								</p>
							</c:when>
							<c:when test="${action.equals(\"sub\")}">
								<p>
									You are paying: &euro; ${subPrice}
									<a href="add_payment?action=${action}">
										<button>Payed</button>
									</a>
								</p>
							</c:when>
							<c:otherwise>
								<p>Something did not work :(</p>
							</c:otherwise>
						</c:choose>

						<!-- Set up a container element for the button -->
						<div id="paypal-button-container"></div>

						<!-- Include the PayPal JavaScript SDK -->
						<script src="https://www.paypal.com/sdk/js?client-id=sb&currency=EUR"></script>

						<script>
                            // Render the PayPal button into #paypal-button-container
                            paypal.Buttons({

                                // Call your server to set up the transaction
                                createOrder: function (data, actions) {
                                    return actions.order.create({
                                        purchase_units: [{
                                            amount: {
                                                value: '${subPrice}'
                                            }
                                        }]
                                    });
                                },

                                // Call your server to finalize the transaction
                                onApprove: function (data, actions) {
                                    return actions.order.capture().then(function (orderData) {
                                        // Successful capture! For demo purposes:
                                        console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));
                                        let transaction = orderData.purchase_units[0].payments.captures[0];
                                        alert('Transaction ' + transaction.status + ': ' + transaction.id + '\n\nSee console for all available details');
                                    });
                                }

                            }).render('#paypal-button-container');
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/html/footer.html" %>
</body>
</html>
