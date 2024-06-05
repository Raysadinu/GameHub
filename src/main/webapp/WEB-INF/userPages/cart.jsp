<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="w" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:gamePages pageTitle="Cart">

    <h1>Cart, ${user.username}</h1>

    <c:if test="${empty games}">
        <p>You don't have any games in your cart.</p>
    </c:if>

    <c:if test="${not empty games}">
        <div class="row">
            <c:forEach var="game" items="${games}">
                <div class="col-sm-6 col-md-4 col-lg-3 col-xl-2">
                    <div class="card">
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">
                                <img src="${pageContext.request.contextPath}/GamePhotos?gameId=${game.gameId}" alt="Game Profile Picture" class="game-picture">
                            </a>
                            <div>
                                <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">${game.gameName}</a>
                                <c:choose>
                                    <c:when test="${gamePrices[game.gameId][0] == 0}">
                                        <p>Free</p>
                                    </c:when>
                                    <c:when test="${gamePrices[game.gameId][1] > 0}">
                                        <div class="price-container">
                                            <b class="price-discount"><fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</b>
                                            <div class="price-values">
                                                <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                                                <b>$<fmt:formatNumber value="${gamePrices[game.gameId][1]}" pattern="##0.00"/></b>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                                    </c:otherwise>
                                </c:choose>

                                <a class="btn btn-danger" href="${pageContext.request.contextPath}/DeleteFromCart?gameId=${game.gameId}&cartId=${cart.cartId}"><i class="bi bi-cart-x"></i>Remove</a>
                                <!-- Add margin-right to create space between buttons -->
                                <div class="btn-group" role="group" style="margin-top: 10px;">
                                    <c:choose>
                                        <c:when test="${wishlist.contains(game)}">
                                            <button class="btn btn-success mr-4" disabled> <i class="fas fa-heart" title="On Wishlist"></i>On Wishlist</button>
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
            </c:forEach>
        </div>
        <p class="total-price">Total Price: $${totalPrice}</p>
        <form action="${pageContext.request.contextPath}/Checkout" method="post">
            <input type="hidden" name="cartId" value="${cart.cartId}">
            <input type="hidden" name="username" value="${cart.user.username}">
            <input type="hidden" name="page" value="checkout">
            <button type="submit" class="btn btn-primary">Checkout</button>
        </form>
    </c:if>
</t:gamePages>
