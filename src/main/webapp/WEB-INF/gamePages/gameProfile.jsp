<%@page contentType="text/html;charset=Windows-1252" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:gameProfile pageTitle="Game Profile">
    <style>
        /* Comment */
        .comment {
            margin-top: 20px;
            margin-bottom: 20px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 20px;
            display: flex; /* Make it a flex container */
            align-items: flex-start; /* Align items to the start (top) */
        }

        .comment img.profile-picture {
            width: 40px; /* Adjust the width of the profile picture */
            height: 40px; /* Adjust the height of the profile picture */
            margin-right: 10px; /* Add some space between the picture and the username */
            border-radius: 50%; /* Make the profile picture round */
        }

        .comment p {
            margin-bottom: 10px;
            flex-grow: 1; /* Allow the comment text to grow and fill available space */
        }

    </style>
    <div class="container">

        <div class="game-details">
            <div class="row">
                <div class="col-md-6">
                    <div class="game-profile-picture">
                        <img src="${pageContext.request.contextPath}/GamePhotos?gameId=${game.gameId}" alt="Game Profile Picture" class="img-fluid">
                    </div>
                    <div class="details-box">
                        <h4>${game.gameName}</h4>
                        <!-- Price -->
                        <div class="price-container-game-profile">
                        <c:choose>
                            <c:when test="${gamePrices[game.gameId][0] == 0}">
                                <p>Free</p>
                            </c:when>
                            <c:when test="${gamePrices[game.gameId][1] > 0}">

                                    <b class="price-discount-game-profile"><fmt:formatNumber value="${gamePrices[game.gameId][2]}" pattern="##"/>%</b>
                                    <div class="price-values">
                                        <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                                        <b>$<fmt:formatNumber value="${gamePrices[game.gameId][1]}" pattern="##0.00"/></b>
                                    </div>

                            </c:when>
                            <c:otherwise>
                                <p>$<fmt:formatNumber value="${gamePrices[game.gameId][0]}" pattern="##0.00"/></p>
                            </c:otherwise>
                        </c:choose>
                        </div>
                        <!-- Admin buttons -->
                        <c:if test="${isAdmin}">
                            <!-- Edit game and add photos buttons -->
                            <div class="admin-buttons">
                                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditGame?gameId=${game.gameId}">Edit Game</a>
                                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddGamePictures?gameId=${game.gameId}">Add Photos</a>
                                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddGameScreenshots?gameId=${game.gameId}">Add Screenshots</a>
                            </div>
                        </c:if>
                        <!-- Wishlist and cart buttons -->
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
                <div class="col-md-6">
                    <div class="trailer">
                        <iframe width="100%" height="315" src="${trailer.link}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </div>
                    <div class="details-box">
                        <h4>Game Details</h4>
                        <p><strong>Publisher:</strong> ${game.publisher}</p>
                        <p><strong>Developer:</strong> ${game.developer}</p>
                        <p><strong>Release Date:</strong> ${game.releaseDate}</p>
                    </div>
                </div>
            </div>

            <!-- Game Screenshots -->
            <div class="slideshow-container">
                <c:forEach var="screenshot" items="${screenshots}" varStatus="status">
                    <div class="slides ${status.index == 0 ? 'active' : ''}">
                        <img src="data:image/${screenshot.picture.imageFormat};base64,${screenshot.picture.base64ImageData}" alt="Screenshot" style="max-width: 700px;" onclick="openModal(this)">
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

            <!-- Modal for Screenshots -->
            <div id="myModal" class="modal">
                <span class="close" onclick="closeModal()">&times;</span>
                <img class="modal-content" id="img01">
                <div id="caption"></div>
            </div>
            <!-- Description -->
            <div class="description">
                <p>${game.description}</p>
            </div>

            <!-- Categories -->
            <c:if test="${not empty categories}">
                <div class="details-box">
                    <h4>Categories:</h4>
                    <ul>
                        <c:forEach var="category" items="${categories}">
                            <li>${category.categoryName}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <!-- System Requirements -->
            <div class="details-box">
                <h4>System Requirements</h4>
                <ul>
                    <c:forEach var="requirement" items="${systemRequirements}">
                        <li>
                            <strong>Storage:</strong> ${game.storage} <br>
                            <strong>Memory:</strong> ${requirement.memory.memory} GB <br>
                            <strong>Processor:</strong> ${requirement.processor.processorName} <br>
                            <strong>Video Card:</strong> ${requirement.videoCard.videoCardName}, ${requirement.videoCard.memory} GB
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!-- Comments -->
            <div class="details-box">
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
                                <c:if test="${not empty userPicturesMap[comment.username]}">
                                    <img class="profile-picture" src="data:image/${userPicturesMap[comment.username].imageFormat};base64,${userPicturesMap[comment.username].base64ImageData}" alt="User's Profile Picture">
                                </c:if>
                                <p><strong>${comment.username}</strong> commented on  ${comment.createdAt}</p>
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
                                <c:if test="${comment.username eq user.username}">
                                    <!-- Display delete button only if the current user wrote the comment -->
                                    <form action="${pageContext.request.contextPath}/DeleteComment" method="post">
                                        <input type="hidden" name="commentId" value="${comment.id}">
                                        <input type="hidden" name="gameId" value="${game.gameId}">
                                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</t:gameProfile>
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