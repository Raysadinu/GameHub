<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:home pageTitle="Store">
    <style>

        /* Additional style enhancements */
        .profile-message {
            max-width: 400px; /* Limit width for better readability */
            padding: 20px; /* Add some padding */
            background-color: #fff; /* White background */
            border-radius: 10px; /* Rounded corners */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add shadow for depth */
        }

        .profile-message p {
            margin-bottom: 20px;
            font-size: 18px; /* Increase font size for better visibility */
        }

        .profile-message .btn {
            background-color: #6c757d;
            border-color: #6c757d;
            color: #fff;
            padding: 12px 24px; /* Increase padding for better clickability */
            text-decoration: none;
            font-size: 18px; /* Increase font size */
            border-radius: 8px; /* Rounded corners */
            display: inline-block;
            transition: background-color 0.3s, color 0.3s; /* Add transition effect */
        }

        .profile-message .btn:hover {
            background-color: #5a6268;
            border-color: #545b62;
            color: #fff;
        }

        /* Center the profile message */
        .profile-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Center vertically on the viewport */
            background-color: #f4f4f4; /* Background color */
        }


    </style>
    <c:choose>
        <c:when test="${userAge == -1}">
            <div class="profile-container">
                <div class="profile-message">
                    <p>Please create your profile to access the store.</p>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditProfile?username=${user.username}">Create Profile</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="search-container">
                <form id="searchForm" onsubmit="return false" method="get" action="${pageContext.request.contextPath}/SearchGames" class="center-content">
                    <div class="search-bar-container">
                        <label for="searchBar"></label>
                        <input type="text" name="keyword" id="searchBar" class="form-control" placeholder="Search games..." value="${param.keyword}">
                    </div>
                </form>
                <div class="col-md-12">
                    <div class="center-content">
                        <div id="gameSearchResults" class="search-results"></div>
                    </div>
                </div>
            </div>

            <div class="categories-container">
                <form method="get" action="${pageContext.request.contextPath}/Filter">
                    <div class="dropdown" style="padding-right: 20px">
                        <button class="dropdown-toggle-home" type="button" id="categoriesDropdown" aria-haspopup="true" aria-expanded="false">Categories</button>
                        <div class="dropdown-menu" aria-labelledby="categoriesDropdown" id="categoriesList">
                            <c:forEach var="category" items="${categories}">
                                <label class="dropdown-item">
                                    <input type="checkbox" name="categoryIds" value="${category.categoryId}" <c:if test="${selectedCategoryIds.contains(category.categoryId)}">checked</c:if> /> ${category.categoryName}
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="dropdown" style="padding-right: 20px">
                        <button class="dropdown-toggle-home" type="button" id="filterDropdown" aria-haspopup="true" aria-expanded="false">Price</button>
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
                        <label for="minPrice"></label><br>
                        <input type="number" id="minPrice" name="minPrice" placeholder="min price" step="0.01" value="${minPrice != null ? minPrice : ''}" style="width: 95px;">
                    </div>
                    <div style="padding-right: 20px">
                        <label for="maxPrice"></label><br>
                        <input type="number" id="maxPrice" name="maxPrice" placeholder="max price" step="0.01" value="${maxPrice != null ? maxPrice : ''}" style="width: 95px;">
                    </div>
                    <input type="hidden" name="games" value="${games}">
                    <button type="submit" class="filter-btn">Filter</button>
                </form>
            </div>

            <div class="game-container" id="gameContainer">
                <div class="row">
                    <c:if test="${filtersActive}">
                        <h2>Filtered games</h2>
                        <div class="row">
                            <c:forEach var="game" items="${games}">
                                <div class="col-sm-12 col-md-6 col-lg-4 col-xl-2 game-card">
                                    <div class="card" data-game-id="${game.gameId}" data-game-name="${game.gameName}">
                                        <div class="card-body">
                                            <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}"><img src="${pageContext.request.contextPath}/GamePhotos?gameId=${game.gameId}" alt="Game Profile Picture" class="game-picture"></a>
                                            <p class="card-text">${game.gameName}</p>
                                            <c:choose>
                                                <c:when test="${gamePrices[game.gameId][0] == 0}">
                                                    <div class="price-container">
                                                        <div class="price-values">
                                                            <p>Free</p>
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${gamePrices[game.gameId][1] > 0}">
                                                    <div class="price-container">
                                                        <b class="price-discount"><fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</b>
                                                        <div class="price-values">
                                                            <s>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></s>
                                                            <b>$<fmt:formatNumber value="${gamePrices[game.gameId][1]}" pattern="##0.00"/></b>
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="price-container">
                                                        <div class="price-values">
                                                            <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                                                        </div>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="wishlist-cart-buttons">
                                                <c:choose>
                                                    <c:when test="${library.contains(game)}">
                                                        <i class="fas fa-bookmark in-library" title="In Library" style="color: #2da800;"></i>
                                                    </c:when>
                                                    <c:when test="${pendingPayment.contains(game)}">
                                                        <i class="fas fa-file-invoice-dollar pending-payment" title="Pending Payment" style="color: #fd941c;"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:choose>
                                                            <c:when test="${wishlist.contains(game)}">
                                                                <i class="fas fa-heart heart-icon filled" title="On Wishlist"></i>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="${pageContext.request.contextPath}/AddToWishlist?gameId=${game.gameId}" title="Add to Wishlist"><i class="far fa-heart heart-icon"></i></a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${cart.contains(game)}">
                                                                <i class="fas fa-shopping-cart cart-icon filled" title="In Cart"></i>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="${pageContext.request.contextPath}/AddToCart?gameId=${game.gameId}" title="Add to Cart"><i class="fas fa-cart-plus cart-icon"></i></a>
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
                                    <div class="col-sm-12 col-md-6 col-lg-4 col-xl-2 game-card">
                                        <div class="card" data-game-id="${game.gameId}" data-game-name="${game.gameName}">
                                            <div class="card-body">
                                                <a href="${pageContext.request.contextPath}/GameProfile?gameId=${game.gameId}"><img src="${pageContext.request.contextPath}/GamePhotos?gameId=${game.gameId}" alt="Game Profile Picture" class="game-picture"></a>
                                                <p class="card-text">${game.gameName}</p>
                                                <c:choose>
                                                    <c:when test="${gamePrices[game.gameId][0] == 0}">
                                                        <div class="price-container">
                                                            <div class="price-values">
                                                                <p>Free</p>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${gamePrices[game.gameId][1] > 0}">
                                                        <div class="price-container">
                                                            <b class="price-discount"><fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</b>
                                                            <div class="price-values">
                                                                <s>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></s>
                                                                <b>$<fmt:formatNumber value="${gamePrices[game.gameId][1]}" pattern="##0.00"/></b>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="price-container">
                                                            <div class="price-values">
                                                                <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                                                            </div>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                                <div class="wishlist-cart-buttons">
                                                    <c:choose>
                                                        <c:when test="${library.contains(game)}">
                                                            <i class="fas fa-bookmark in-library" title="In Library" style="color: #2da800;"></i>
                                                        </c:when>
                                                        <c:when test="${pendingPayment.contains(game)}">
                                                            <i class="fas fa-file-invoice-dollar pending-payment" title="Pending Payment" style="color: #fd941c;"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${wishlist.contains(game)}">
                                                                    <i class="fas fa-heart heart-icon filled" title="On Wishlist"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="${pageContext.request.contextPath}/AddToWishlist?gameId=${game.gameId}" title="Add to Wishlist"><i class="far fa-heart heart-icon"></i></a>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <c:choose>
                                                                <c:when test="${cart.contains(game)}">
                                                                    <i class="fas fa-shopping-cart cart-icon filled" title="In Cart"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="${pageContext.request.contextPath}/AddToCart?gameId=${game.gameId}" title="Add to Cart"><i class="fas fa-cart-plus cart-icon"></i></a>
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
        </c:otherwise>
    </c:choose>

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
                            suggestionElement.classList.add('game-item'); // Add game-item class
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