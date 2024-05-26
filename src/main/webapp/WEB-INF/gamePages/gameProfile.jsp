<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Game Profile">
    <div class="container text-center">
        <div>
            <h3>${game.gameName}</h3>
            <c:if test="${isAdmin}">
                <div><a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditGame?gameId=${game.gameId}">Edit Game</a></div>
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
                <p>Price: Free</p>
            </c:when>
            <c:when test="${gamePrices[game.gameId][1] > 0}">
                <p style="color: grey; text-decoration: line-through;">Price: $<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                <p>Discount: <fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</p>
                <p>Discounted Price: $<fmt:formatNumber value="${gamePrices[game.gameId][1]}" pattern="##0.00"/></p>
            </c:when>
            <c:otherwise>
                <p>Price: $<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
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
                            <span id="recommendation-icon-${comment.id}"></span>
                        </div>
                        <div class="reactions">
                            <button class="btn btn-primary">Like</button>
                            <button class="btn btn-danger">Dislike</button>
                            <button class="btn btn-success">Helpful</button>
                        </div>
                        <button class="btn btn-secondary reply-btn">Reply</button>
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
