<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="w" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Wishlist">
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
                                <c:when test="${gamePrices[game.gameId][1] > 0}">
                                    <p style="color: grey; text-decoration: line-through;">Price: $<c:out value="${gamePrices[game.gameId][0]}" /></p>
                                    <p>Discount: <fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</p>
                                    <p>Discounted Price: $<c:out value="${gamePrices[game.gameId][1]}" /></p>
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
