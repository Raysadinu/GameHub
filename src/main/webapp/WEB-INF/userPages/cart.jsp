<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/16/2024
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Wishlist">
    <h1>Cart, ${user.username}</h1>

    <c:if test="${empty games}">
        <p>You don't have any games in your cart.</p>
    </c:if>

    <c:if test="${not empty games}">
        <cr:forEach var="game" items="${games}">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">${game.gameName}</a>
                            <c:choose>
                                <c:when test="${gamePrices[game.gameId][1] > 0}">
                                 <p style="color: grey; text-decoration: line-through;">Price: $<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                                 <p>Discount: <fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</p>
                                 <p>Discounted Price: $<fmt:formatNumber value="${gamePrices[game.gameId][1]}" pattern="##0.00"/></p>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${gamePrices[game.gameId][0] == 0}">
                                            <p>Price: Free</p>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="price" value="${gamePrices[game.gameId][0]}" />
                                            <c:choose>
                                                <c:when test="${price == price.intValue()}">
                                                    <p>Price: $<c:out value="${price.intValue()}" /></p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>Price: $<c:out value="${price}" /></p>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/DeleteFromCart?gameId=${game.gameId}&cartId=${cart.cartId}">Remove</a>
                            <div class="btn-group" role="group">
                                <c:choose>
                                    <c:when test="${wishlist.contains(game)}">
                                        <button class="btn btn-success mr-4" disabled>On Wishlist</button>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${pageContext.request.contextPath}/AddToWishlist" method="post">
                                            <input type="hidden" name="gameId" value="${game.gameId}">
                                            <button type="submit" class="btn btn-primary">Add to Wishlist</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </cr:forEach>
        <p>Total Price: $${totalPrice}</p>
        <form action="${pageContext.request.contextPath}/Checkout" method="post">
            <input type="hidden" name="cartId" value="${cart.cartId}">
            <input type="hidden" name="username" value="${cart.user.username}">
            <input type="hidden" name="page" value="checkout">
            <button type="submit" class="btn btn-primary">Checkout</button>
        </form>
    </c:if>
</t:template>

