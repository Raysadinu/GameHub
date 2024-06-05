<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="w" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:gamePages pageTitle="Wishlist">

    <h1>Wishlist, ${user.username}</h1>

    <c:if test="${empty games}">
        <p>You don't have any games in your wishlist.</p>
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

                                <c:if test="${not pendingPayment.contains(game)}">
                                    <form action="${pageContext.request.contextPath}/DeleteFromWishlist" method="get">
                                        <input type="hidden" name="gameId" value="${game.gameId}">
                                        <input type="hidden" name="wishlistId" value="${wishlist.wishlistId}">
                                        <input type="hidden" name="page" value="wishlist">
                                        <button type="submit" class="btn btn-danger btn-remove">
                                            <i class="bi bi-heartbreak"></i> Remove
                                        </button>
                                    </form>
                                </c:if>

                                <div class="btn-group" role="group">
                                    <c:choose>
                                        <c:when test="${cart.contains(game)}">
                                            <a href="#" class="btn btn-primary"><i class="fas fa-shopping-cart"></i> In Cart</a>
                                        </c:when>
                                        <c:when test="${pendingPayment.contains(game)}">
                                            <a href="#" class="btn btn-warning"><i class="fas fa-file-invoice-dollar"></i> Pending Payment</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/AddToCart?gameId=${game.gameId}" class="btn btn-primary"><i class="fas fa-cart-plus"></i> Add to Cart</a>
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
    </c:if>
</t:gamePages>
