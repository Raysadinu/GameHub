<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="w" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Wishlist">
    <style>
        .price-discount {
            background-color: blue;
            color: white;
            padding: 0 5px;
            border-radius: 5px;
            font-size: 12px;
            margin-right: 15px;
        }

        .price-values {
            display: flex;
            align-items: center;
        }

        .price-values p, .price-values s {
            margin-right: 10px;
            margin-bottom: 0;
        }

        .price-values p {
            text-decoration: line-through;
            color: grey;
        }
    </style>
    <h1>Wishlist, ${user.username}</h1>

    <c:if test="${empty games}">
        <p>You don't have any games in your wishlist.</p>
    </c:if>

    <c:if test="${not empty games}">
        <w:forEach var="game" items="${games}">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-body">
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

                            <form action="${pageContext.request.contextPath}/DeleteFromWishlist" method="post">
                                <input type="hidden" name="gameId" value="${game.gameId}">
                                <input type="hidden" name="wishlistId" value="${wishlist.wishlistId}">
                                <input type="hidden" name="page" value="wishlist">
                                <button type="submit" class="btn btn-danger mr-4">Remove</button>
                            </form>

                            <div class="btn-group" role="group">
                                <c:choose>
                                    <c:when test="${cart.contains(game)}">
                                        <button class="btn btn-primary" disabled>Already in Cart</button>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${pageContext.request.contextPath}/AddToCart" method="post">
                                            <input type="hidden" name="gameId" value="${game.gameId}">
                                            <button type="submit" class="btn btn-primary">Add to Cart</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </w:forEach>
        <p>Total Price: $${totalPrice}</p>
    </c:if>
</t:template>
