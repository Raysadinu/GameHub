<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template pageTitle="Payment Requests">
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

