<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template pageTitle="Payment Requests">
  <style>
    .container {
      max-width: 1400px;
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

    .btn {
      padding: 8px 16px;
      border-radius: 5px;
      text-decoration: none;
      color: #fff;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .btn:hover {
      background-color: #007bff;
    }

  </style>


  <div class="container text-center">
    <div>
      <div>
        <h1>Payment Requests</h1>
        <table>
          <thead>
          <tr>
            <th rowspan="2">Payment Request ID</th>
            <th rowspan="2">User</th>
            <th rowspan="2">Status</th>
            <th rowspan="2">Games</th>
            <th colspan="2">Card Details</th>
            <th rowspan="2">Total Price</th>
            <th colspan="2">Actions</th>
          </tr>
          <tr>
            <th>Card Number</th>
            <th>Expiration Date</th>
            <th>ACCEPT</th>
            <th>DECLINE</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${paymentRequests}" var="paymentRequest">
            <c:choose>
              <c:when test="${paymentRequest.status eq 'PENDING'}">
                <c:set var="numGames" value="${fn:length(paymentRequest.games)}" />
                <c:set var="rowspan" value="${numGames}" />
                <c:set var="totalPrice" value="0" />
                <c:forEach items="${paymentRequest.games}" var="game" varStatus="loop">
                  <tr>
                    <c:if test="${loop.index eq 0}">
                      <td rowspan="${rowspan}">${paymentRequest.paymentReqId}</td>
                      <td rowspan="${rowspan}">${paymentRequest.user.username}</td>
                      <td rowspan="${rowspan}">${paymentRequest.status}</td>
                      <td rowspan="${rowspan}">
                        <c:forEach items="${paymentRequest.games}" var="game">
                          <div class="game-name">${game.gameName}</div>
                        </c:forEach>
                      </td>
                      <td rowspan="${rowspan}" class="card-number">
                        **** **** **** ${fn:substring(paymentRequest.card.cardNumber, fn:length(paymentRequest.card.cardNumber) - 4, fn:length(paymentRequest.card.cardNumber))}
                      </td>
                      <td rowspan="${rowspan}">
                          ${paymentRequest.card.expirationDate}
                      </td>
                      <td rowspan="${rowspan}">
                        <p>$${paymentRequest.totalPrice}</p>
                      </td>
                      <td>
                        <a class="btn btn-success" href="${pageContext.request.contextPath}/PrAcceptOrDecline?paymentrequestId=${paymentRequest.paymentReqId}&action=A">ACCEPT</a>
                      </td>
                      <td>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/PrAcceptOrDecline?paymentrequestId=${paymentRequest.paymentReqId}&action=D">DECLINE</a>
                      </td>
                    </c:if>
                  </tr>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <c:set var="numGames" value="${fn:length(paymentRequest.games)}" />
                <c:set var="rowspan" value="${numGames}" />
                <c:set var="totalPrice" value="0" />
                <c:forEach items="${paymentRequest.games}" var="game" varStatus="loop">
                  <tr>
                    <c:if test="${loop.index eq 0}">
                      <td rowspan="${rowspan}">${paymentRequest.paymentReqId}</td>
                      <td rowspan="${rowspan}">${paymentRequest.user.username}</td>
                      <td rowspan="${rowspan}">${paymentRequest.status}</td>
                      <td rowspan="${rowspan}">
                        <c:forEach items="${paymentRequest.games}" var="game">
                          <div class="game-name">${game.gameName}</div>
                        </c:forEach>
                      </td>
                      <td rowspan="${rowspan}" class="card-number">
                        **** **** **** ${fn:substring(paymentRequest.card.cardNumber, fn:length(paymentRequest.card.cardNumber) - 4, fn:length(paymentRequest.card.cardNumber))}
                      </td>
                      <td rowspan="${rowspan}">
                          ${paymentRequest.card.expirationDate}
                      </td>
                      <td rowspan="${rowspan}">
                        <p>$${paymentRequest.totalPrice}</p>
                      </td>
                      <td colspan="2">
                        -
                      </td>
                    </c:if>
                  </tr>
                </c:forEach>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</t:template>
