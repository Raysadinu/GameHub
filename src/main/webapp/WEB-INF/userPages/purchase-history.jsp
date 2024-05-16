<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template pageTitle="Purchase History">
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
                        <c:set var="numGames" value="${fn:length(paymentRequest.games)}" /> <!-- Numărul de jocuri din cererea de plată -->
                        <c:set var="rowspan" value="${numGames}" /> <!-- Setează rowspan-ul inițial -->
                        <c:set var="totalPrice" value="0" />
                        <c:forEach items="${paymentRequest.games}" var="game" varStatus="loop">
                            <tr>
                                <c:if test="${loop.index eq 0}"> <!-- Verifică dacă este prima iterație -->
                                    <!-- La prima iterație, afișează datele comune și rowspan-ul -->
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
