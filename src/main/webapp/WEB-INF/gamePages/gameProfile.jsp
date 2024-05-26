<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Game Profile">
    <style>
        .price-container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin-bottom: 10px;
        }

        .price-discount {
            flex-grow: 1;
            margin-right: 10px;
            background-color: blue;
            color: white;
            padding: 5px;
            text-align: center;
            width: 40px;
            height: 25px;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 5px;
            font-size:15px;
            margin-top:25px;
            margin-bottom: 5px;

        }
        .price-values {
            display: flex;
            align-items: center;
        }
        .price-values p, .price-values b {
            margin-right:  15px;
            margin-bottom: 0;
        }
        .price-values p {
            text-decoration: line-through;
            color: grey;
        }
    </style>
    <div class="container text-center" style="margin-top: 100px">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <!-- Display game profile picture -->
                <img src="${pageContext.request.contextPath}/GamePhotos?gameId=${game.gameId}" width="200">
            </div>
        </div>

        <div>
            <h3>${game.gameName}</h3>
            <c:if test="${isAdmin}">
                <div>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditGame?gameId=${game.gameId}">Edit Game</a>
                </div>
                <div>
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddGamePictures?gameId=${game.gameId}">Add Photos</a>
                </div>
            </c:if>

            <div class="wishlist-cart-buttons">
                <c:choose>
                    <c:when test="${library.contains(game)}">
                        <button class="btn btn-primary" disabled>In Library</button>
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
        <iframe width="560" height="315" src="${trailer.link}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h4>Screenshots</h4>
            <div class="row">
                <c:forEach var="screenshot" items="${screenshots}">
                    <div class="col-md-3">
                        <c:if test="${not empty screenshot}">
                            <img src="data:image/${screenshot.picture.imageFormat};base64,${screenshot.picture.base64ImageData}" alt="Screenshot" width="560" height="315">
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <c:if test="${isAdmin}">
        <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddGameScreenshots?gameId=${game.gameId}">Add Screenshots</a>
        </div>
    </c:if>

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


        <h4>Comments</h4>
        <form action="${pageContext.request.contextPath}/AddComment" method="post" class="comment-form">
            <div class="form-group">
                <label for="comment">Your Comment:</label><br>
                <textarea id="comment" name="content" rows="4" cols="50" placeholder="Add your comment here" class="form-control"></textarea>
            </div><br>
            <div class="form-group">
                <label for="recommendation">Recommendation:</label>
                <select id="recommendation" name="recommendation" class="form-control">
                    <option value="recommended">Recommend</option>
                    <option value="notRecommended">Do not recommend</option>
                </select>
            </div><br>
            <input type="hidden" name="gameId" value="${game.gameId}">
            <input type="submit" value="Post Comment" class="btn btn-primary">
        </form>

        <c:if test="${not empty comments}">
            <div class="comments-section">
                <h4>Comments:</h4>
                <c:forEach var="comment" items="${comments}">
                    <div class="comment">
                        <p><strong>${comment.username}</strong> commented on ${comment.createdAt}</p>

                        <p>${comment.content}</p>
                        <div class="recommendation">
                            <span>Recommendation:</span>
                            <!-- Display thumbs-up icon if recommended, thumbs-down if notRecommended -->
                            <c:if test="${comment.recommended}">
                                <i class="bi bi-hand-thumbs-up-fill"  style="color: green;"></i>
                            </c:if>
                            <c:if test="${comment.notRecommended}">
                                <i class="bi bi-hand-thumbs-down-fill" style="color: red;"></i>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
    <script>
        // Function to add thumbs-up or thumbs-down icons based on recommendation
        function addThumbIcon(recommendation) {
            const recommendationIcon = document.createElement('i');
            recommendationIcon.classList.add('fas'); // Font Awesome class for icons
            if (recommendation === 'recommended') {
                recommendationIcon.classList.add('fa-thumbs-up');
                recommendationIcon.style.color = 'green'; // Green color for thumbs-up
            } else if (recommendation === 'notRecommended') {
                recommendationIcon.classList.add('fa-thumbs-down');
                recommendationIcon.style.color = 'red'; // Red color for thumbs-down
            }
            return recommendationIcon;
        }

        // Example usage: Add thumbs-up or thumbs-down icon based on the selected recommendation
        const recommendationSelect = document.getElementById('recommendation');
        recommendationSelect.addEventListener('change', function() {
            const selectedValue = this.value;
            const iconContainer = document.getElementById('recommendation-icon');
            iconContainer.innerHTML = ''; // Clear previous icon
            const recommendationIcon = addThumbIcon(selectedValue);
            iconContainer.appendChild(recommendationIcon);
        });
    </script>
</t:template>
