<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Game Profile">
    <div class="container text-center">
        <div>
            <h3>${game.gameName}</h3>
        </div>
            <c:choose>
                <c:when test="${price.price == 0}">
                    <p>Free</p>
                </c:when>
                <c:otherwise>
                    <c:if test="${price.discount > 0}">
                        <p>Price:</p>
                        <span style="text-decoration: line-through;">${price.price}</span>
                        <span>$${price.discount_price}</span>
                        <p>Discount:<fmt:formatNumber value="${price.discount}%" pattern="##"/>%</p>
                    </c:if>
                    <c:if test="${price.discount == 0}">
                        $${price.price}
                    </c:if>
                </c:otherwise>
            </c:choose>
        <p>Publisher: ${game.publisher}</p>
        <p>Developer: ${game.developer}</p>
        <p>Release Date: ${game.releaseDate}</p>
        <p>Description: ${game.description}</p>

        <c:if test="${not empty categories}">
            <div>
                <h4>Categories:</h4>
                    <c:forEach var="category" items="${categories}">
                        ${category.categoryName}
                    </c:forEach>
            </div>
        </c:if>

        <c:if test="${isAdmin}">
            <div><a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditGame?gameId=${game.gameId}">Edit Game</a></div>
        </c:if>
    </div>
</t:template>
