<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cd" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:template pageTitle="Cards">
    <style>
        .container {
            max-width: 800px;
            margin: 100px auto;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h4 {
            margin-bottom: 20px;
            color: #333;
        }

        .table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .table th, .table td {
            padding: 10px;
            border: 1px solid #dee2e6;
        }

        .table th {
            background-color: #007bff;
            color: #fff;
            font-weight: bold;
        }

        .table td {
            background-color: #fff;
            color: #333;
        }

        .table tbody tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        .table tbody tr:hover {
            background-color: #e9ecef;
        }
    </style>

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
                            <td>**** **** **** ${fn:substring(card.cardNumber, fn:length(card.cardNumber) - 4, fn:length(card.cardNumber))}</td>
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
