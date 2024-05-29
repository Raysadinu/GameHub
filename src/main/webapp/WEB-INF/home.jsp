<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:home pageTitle="Store">


    <c:if test="${userAge == -1}">
        <p>Age: -1</p>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditProfile?username=${user.username}">Add your birth date</a>
    </c:if>

    <div class="search-container">
        <form id="searchForm" onsubmit="return false" method="get" action="${pageContext.request.contextPath}/SearchGames" class="center-content">
            <div class="search-bar-container">
                <label for="searchBar"></label>
                <input type="text" name="keyword" id="searchBar" class="form-control" placeholder="Search games..." value="${param.keyword}">
            </div>
        </form>
        <div class="col-md-12">
            <div class="center-content">
                <div id="gameSearchResults" style="display: flex; flex-direction: column;"></div>
            </div>
        </div>
    </div>

    <div class="categories-container">
        <form method="get" action="${pageContext.request.contextPath}/Filter">
            <div class="dropdown" style="padding-right: 20px">
                <button class="dropdown-toggle" type="button" id="categoriesDropdown" aria-haspopup="true" aria-expanded="false">Categories</button>
                <div class="dropdown-menu" aria-labelledby="categoriesDropdown" id="categoriesList">
                    <c:forEach var="category" items="${categories}">
                        <label class="dropdown-item">
                            <input type="checkbox" name="categoryIds" value="${category.categoryId}" <c:if test="${selectedCategoryIds.contains(category.categoryId)}">checked</c:if> /> ${category.categoryName}
                        </label>
                    </c:forEach>
                </div>
            </div>
            <div class="dropdown" style="padding-right: 20px">
                <button class="dropdown-toggle" type="button" id="filterDropdown" aria-haspopup="true" aria-expanded="false">Price</button>
                <div class="dropdown-menu" aria-labelledby="filterDropdown" id="filterMenu">
                    <label class="dropdown-item">
                        <input type="checkbox" name="freeOnly" id="freeCheckbox" <c:if test="${freeOnly}">checked</c:if> /> Free
                    </label>
                    <label class="dropdown-item">
                        <input type="checkbox" name="discountedOnly" id="discountCheckbox" <c:if test="${discountedOnly}">checked</c:if> /> Discounted
                    </label>
                </div>
            </div>
            <div style="padding-right: 20px">
                <label for="minPrice">Min Price:</label><br>
                <input type="number" id="minPrice" name="minPrice" step="0.01" value="${minPrice != null ? minPrice : ''}" style="width: 80px;">
            </div>
            <div style="padding-right: 20px">
                <label for="maxPrice">Max Price:</label><br>
                <input type="number" id="maxPrice" name="maxPrice" step="0.01" value="${maxPrice != null ? maxPrice : ''}" style="width: 80px;">
            </div>
            <input type="hidden" name="games" value="${games}">
            <button type="submit" class="btn btn-primary">Filter</button>
        </form>
    </div>

    <div class="game-container" id="gameContainer">
        <div class="row">
            <c:if test="${filtersActive}">
                <h2>Filtered games</h2>
                <div class="row">
                    <c:forEach var="game" items="${games}">
                        <div class="col-sm-3 game-card">
                            <div class="card" data-game-id="${game.gameId}" data-game-name="${game.gameName}">
                                <div class="card-body">
                                    <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}">${game.gameName}</a>
                                    <c:choose>
                                        <c:when test="${gamePrices[game.gameId][0] == 0}">
                                            <p>Free</p>
                                        </c:when>
                                        <c:when test="${gamePrices[game.gameId][1] > 0}">
                                            <b class="price-discount"><fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</b>
                                            <div class="price-values">
                                                <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                                                <b>$<fmt:formatNumber value="${gamePrices[game.gameId][1]}" pattern="##0.00"/></b>
                                            </div>
                                        </c:when>

                                        <c:otherwise>
                                            <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="wishlist-cart-buttons">
                                        <c:choose>
                                            <c:when test="${library.contains(game)}">
                                                <button class="btn btn-primary" disabled>In Library</button>
                                            </c:when>
                                            <c:when test="${pendingPayment.contains(game)}">
                                                <button class="btn btn-warning" disabled>Pending Payment</button>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${wishlist.contains(game)}">
                                                        <button class="btn btn-success" disabled>On Wishlist</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form action="${pageContext.request.contextPath}/AddToWishlist" method="post">
                                                            <input type="hidden" name="gameId" value="${game.gameId}">
                                                            <button type="submit" class="btn btn-secondary">Add to Wishlist</button>
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
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${userAge > 0}">
                <c:if test="${!filtersActive}">
                    <div class="row">
                        <c:forEach var="game" items="${games}">
                            <div class="col-sm-3 game-card">
                                <div class="card" data-game-id="${game.gameId}" data-game-name="${game.gameName}">
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
                                        <div class="wishlist-cart-buttons">
                                            <c:choose>
                                                <c:when test="${library.contains(game)}">
                                                    <button class="btn btn-primary" disabled>In Library</button>
                                                </c:when>
                                                <c:when test="${pendingPayment.contains(game)}">
                                                    <button class="btn btn-warning" disabled>Pending Payment</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${wishlist.contains(game)}">
                                                            <button class="btn btn-success" disabled>On Wishlist</button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form action="${pageContext.request.contextPath}/AddToWishlist" method="post">
                                                                <input type="hidden" name="gameId" value="${game.gameId}">
                                                                <button type="submit" class="btn btn-secondary">Add to Wishlist</button>
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
                        </c:forEach>
                    </div>
                </c:if>
            </c:if>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var filterDropdown = document.getElementById('filterDropdown');
            var filterMenu = document.getElementById('filterMenu');

            filterDropdown.addEventListener('click', function(event) {
                event.stopPropagation();
                filterMenu.classList.toggle('show');
            });

            window.addEventListener('click', function(event) {
                if (!filterDropdown.contains(event.target) && !filterMenu.contains(event.target)) {
                    filterMenu.classList.remove('show');
                }
            });
        });

        document.addEventListener("DOMContentLoaded", function() {
            var categoriesDropdown = document.getElementById('categoriesDropdown');
            var categoriesList = document.getElementById('categoriesList');

            categoriesDropdown.addEventListener('click', function(event) {
                event.stopPropagation();
                categoriesList.classList.toggle('show');
            });

            window.addEventListener('click', function(event) {
                if (!categoriesDropdown.contains(event.target) && !categoriesList.contains(event.target)) {
                    categoriesList.classList.remove('show');
                }
            });
        });

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
                            suggestionElement.dataset.gameId = game.gameId;
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
                document.getElementById('gameSearchResults').innerHTML = '';
            }
        });
    </script>
</t:home>
