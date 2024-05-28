<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/14/2024
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cd" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Cards">
    <div class="container text-center">
        <div>
            <div>
                <h4>Card Details:</h4>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Card Number</th>
                        <th>Expiration Date</th>
                        <th>Card Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <cd:forEach var="card" items="${cardDetails}" varStatus="loop">
                        <tr>
                            <td>${card.cardId}</td>
                            <td>${card.cardNumber}</td>
                            <td>${card.expirationDate}</td>
                            <td>${card.cardName}</td>
                        </tr>
                    </cd:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:template>
