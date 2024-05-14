<%@ page import="java.util.Map" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="gd" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Store">
  <h1>Store</h1>
  <gd:forEach var="game" items="${games}">
    <div class="row">
      <div class="col-sm-6">
        <div class="card">
          <div class="card-body">
            <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">${game.gameName}</a>

            <p>Price: $${gamePrices[game.gameId]}</p>

            <form action="${pageContext.request.contextPath}/AddToWishlist" method="post">

              <input type="hidden" name="gameId" value="${game.gameId}">
              <button type="submit" class="btn btn-secondary mr-4">Add to Wishlist</button>
            </form>
            <div class="btn-group" role="group">
              <form action="${pageContext.request.contextPath}/AddToCart" method="post">

                <input type="hidden" name="gameId" value="${game.gameId}">
                <button type="submit" class="btn btn-primary">Add to Cart</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </gd:forEach>
</t:template>
