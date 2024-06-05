<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template pageTitle="Purchase History">
<style>
    .container {
        max-width: 1000px;
        margin: 100px auto;
        padding: 20px;
        background-color: #f8f9fa;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    h1 {
        margin-bottom: 20px;
        color: #333;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        padding: 10px;
        border: 1px solid #dee2e6;
        text-align: center;
    }

    th {
        background-color: #007bff;
        color: #fff;
        font-weight: bold;
    }

    td {
        background-color: #fff;
        color: #333;
    }

    tbody tr:nth-child(even) {
        background-color: #f8f9fa;
    }
</style>
    <div class="container text-center">
        <div>
            <div>
                <h1>Purchase History</h1>
                <table>
                    <thead>
                    <tr>
                        <th rowspan="2">Payment Request ID</th>
                        <th rowspan="2">Status</th>
                        <th rowspan="2">Games</th>
                        <th colspan="2">Card Details</th>
                        <th rowspan="2">Total Price</th>
                    </tr>
                    <tr>
                        <th>Card Number</th>
                        <th>Expiration Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${paymentRequests}" var="paymentRequest">
                        <c:set var="numGames" value="${fn:length(paymentRequest.games)}" />
                        <c:set var="rowspan" value="${numGames}" />
                        <c:set var="totalPrice" value="0" />
                        <c:forEach items="${paymentRequest.games}" var="game" varStatus="loop">
                            <tr>
                                <c:if test="${loop.index eq 0}">
                                    <td rowspan="${rowspan}">${paymentRequest.paymentReqId}</td>
                                    <td rowspan="${rowspan}">${paymentRequest.status}</td>
                                    <td rowspan="${rowspan}">
                                        <c:forEach items="${paymentRequest.games}" var="game">
                                            ${game.gameName}<br>
                                        </c:forEach>
                                    </td>
                                    <td rowspan="${rowspan}">
                                            ${paymentRequest.card.cardNumber}
                                    </td>
                                    <td rowspan="${rowspan}">
                                            ${paymentRequest.card.expirationDate}
                                    </td>
                                    <td rowspan="${rowspan}">
                                        <p>$${paymentRequest.totalPrice}</p>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:template>
