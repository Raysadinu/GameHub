<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="gd" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Store">
  <div class="container mt-3 mb-3">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <form id="searchForm" onsubmit="return false" method="get" action="${pageContext.request.contextPath}/SearchGames">
          <label for="searchBar"></label>
          <input type="text" name="keyword" id="searchBar" class="form-control" placeholder="Search games..." value="${param.keyword}">
        </form>
        <div id="gameSearchResults" class="mt-2"></div>
      </div>
    </div>
  </div>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-3">
        <h3>Categories</h3>
          <ul class="list-group">
            <c:forEach var="category" items="${categories}">
              <li class="list-group-item"><a href="${pageContext.request.contextPath}/Home?categoryId=${category.categoryId}">${category.categoryName}</a></li>
            </c:forEach>
          </ul>
      </div>
      <div class="col-md-9">
        <h1>Store</h1>
          <gd:forEach var="game" items="${games}">
            <div class="row">
              <div class="col-sm-6">
                <div class="card" data-game-id="${game.gameId}" data-game-name="${game.gameName}">
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
                      <c:choose>
                        <c:when test="${library.contains(game)}">
                          <button class="btn btn-primary" disabled>In Library</button>
                        </c:when>
                        <c:otherwise>
                          <c:choose>
                            <c:when test="${wishlist.contains(game)}">
                              <button class="btn btn-success mr-4" disabled>On Wishlist</button>
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
                              <button class="btn btn-primary" disabled>Already in Cart</button>
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
          </gd:forEach>
      </div>
    </div>
  </div>
  <script>
    document.getElementById('searchBar').addEventListener('input', function() {
      var keyword = this.value.trim();
      if (keyword !== '') {
        fetch('${pageContext.request.contextPath}/SearchGames?keyword=' + keyword)
                .then(response => response.json())
                .then(data => {
                  var suggestionsDiv = document.getElementById('gameSearchResults');
                  suggestionsDiv.innerHTML = '';
                  data.forEach(game => {
                    var suggestionElement = document.createElement('div');
                    suggestionElement.textContent = game.gameName;
                    suggestionElement.dataset.gameId = game.gameId;  // Ensure gameId is set
                    suggestionElement.addEventListener('click', function() {
                      var gameId = this.dataset.gameId;
                      if (gameId) {
                        window.location.href = '${pageContext.request.contextPath}/GameProfile?gameId=' + gameId;
                      } else {
                        console.error("Game ID is undefined!");
                      }
                    });
                    suggestionsDiv.appendChild(suggestionElement);
                  });
                })
                .catch(error => {
                  console.error('Error fetching search suggestions:', error);
                });
      } else {
        document.getElementById('gameSearchResults').innerHTML = ''; // Clear suggestions if input is empty
      }
    });
  </script>
</t:template>
