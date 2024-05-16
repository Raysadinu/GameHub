<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="gd" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Store">
  <h1>Store</h1>
  <gd:forEach var="game" items="${games}">
    <div class="row">
      <div class="col-sm-6">
        <div class="card">
          <div class="card-body">
            <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">${game.gameName}</a>
            <c:choose>
              <c:when test="${library.contains(game)}">
                <button class="btn btn-primary" disabled>In Library</button>
              </c:when>
              <c:otherwise>
                <c:choose>
                  <c:when test="${wishlist.contains(game)}">
                    <form action="${pageContext.request.contextPath}/RemoveFromWishlist" method="post">
                      <input type="hidden" name="gameId" value="${game.gameId}">
                      <button type="submit" class="btn btn-danger mr-4">Remove from Wishlist</button>
                    </form>
                  </c:when>
                  <c:otherwise>
                    <form action="${pageContext.request.contextPath}/AddToWishlist" method="post">
                      <input type="hidden" name="gameId" value="${game.gameId}">
                      <button type="submit" class="btn btn-secondary mr-4">Add to Wishlist</button>
                    </form>
                  </c:otherwise>
                </c:choose>
                <c:choose>
                  <c:when test="${cart.contains(game)}">
                    <form action="${pageContext.request.contextPath}/RemoveFromCart" method="post">
                      <input type="hidden" name="gameId" value="${game.gameId}">
                      <button type="submit" class="btn btn-danger mr-4">Remove from Cart</button>
                    </form>
                  </c:when>
                  <c:otherwise>
                    <form action="${pageContext.request.contextPath}/AddToCart" method="post">
                      <input type="hidden" name="gameId" value="${game.gameId}">
                      <button type="submit" class="btn btn-primary">Add to Cart</button>
                    </form>
                  </c:otherwise>
                </c:choose>
              </c:otherwise>
            </c:choose>
            </div>
          </div>
        </div>
      </div>
    </div>
  </gd:forEach>
</t:template>
