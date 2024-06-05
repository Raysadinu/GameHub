<%@page contentType="text/html;charset=Windows-1252" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:template pageTitle="Game Profile">


    <div class="container text-center" style="margin-top: 100px">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <img src="${pageContext.request.contextPath}/GamePhotos?gameId=${game.gameId}" alt = "Game Profile Picture" width="250">
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
        <c:choose>
            <c:when test="${gamePrices[game.gameId][0] == 0}">
                <p>Free</p>
            </c:when>
            <c:when test="${gamePrices[game.gameId][1] > 0}">
                <div class="price-container-game-profile">
                    <b class="price-discount-game-profile"><fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</b>
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
        <iframe width="700" height="415" src="${trailer.link}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

        <div class="slideshow-container">
            <c:forEach var="screenshot" items="${screenshots}" varStatus="status">
                <div class="slides ${status.index == 0 ? 'active' : ''}">
                    <img src="data:image/${screenshot.picture.imageFormat};base64,${screenshot.picture.base64ImageData}" alt="Screenshots" style="width:700px" onclick="openModal(this)">
                </div>
            </c:forEach>

            <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
            <a class="next" onclick="plusSlides(1)">&#10095;</a>
        </div>
        <br>

        <div class="thumbnail-container">
            <c:forEach var="screenshot" items="${screenshots}" varStatus="status">
                <img class="thumbnail ${status.index == 0 ? 'active-thumb' : ''}" src="data:image/${screenshot.picture.imageFormat};base64,${screenshot.picture.base64ImageData}" onclick="currentSlide(${status.index + 1})">
            </c:forEach>
        </div>


        <div id="myModal" class="modal">
            <span class="close" onclick="closeModal()">&times;</span>
            <img class="modal-content" id="img01">
            <div id="caption"></div>
        </div>


        <c:if test="${isAdmin}">
            <div>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddGameScreenshots?gameId=${game.gameId}">Add Screenshots</a>
            </div>
        </c:if>
            <p>Times Purchased: ${timesPurchased}</p>
            <p>Publisher: ${game.publisher}</p>
            <p>Developer: ${game.developer}</p>
            <p>Release Date: ${game.releaseDate}</p>
            <p>${game.description}</p>

            <c:if test="${not empty categories}">
                <div>
                    <h4>Categories:</h4>
                        <c:forEach var="category" items="${categories}">
                            ${category.categoryName}
                        </c:forEach>
                </div>
            </c:if>

        <div class="system-requirements">
            <h4>System Requirements</h4>
                    Storage: ${game.storage}
                <c:forEach var="requirement" items="${systemRequirements}">
                    Memory: ${requirement.memory.memory} GB,
                    Processor: ${requirement.processor.processorName},
                    VideoCard: ${requirement.videoCard.videoCardName}, ${requirement.videoCard.memory} GB
                </c:forEach>
        </div>


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
        let slideIndex = 1;
        showSlides(slideIndex);

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            let i;
            let slides = document.getElementsByClassName("slides");
            let thumbnails = document.getElementsByClassName("thumbnail");
            if (n > slides.length) {slideIndex = 1}
            if (n < 1) {slideIndex = slides.length}
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            for (i = 0; i < thumbnails.length; i++) {
                thumbnails[i].className = thumbnails[i].className.replace(" active-thumb", "");
            }
            slides[slideIndex-1].style.display = "block";
            thumbnails[slideIndex-1].className += " active-thumb";
        }

        function openModal(img) {
            let modal = document.getElementById("myModal");
            let modalImg = document.getElementById("img01");
            let captionText = document.getElementById("caption");
            modal.style.display = "block";
            modalImg.src = img.src;
            captionText.innerHTML = img.alt;
        }

        function closeModal() {
            let modal = document.getElementById("myModal");
            modal.style.display = "none";
        }
    </script>

</t:template>
