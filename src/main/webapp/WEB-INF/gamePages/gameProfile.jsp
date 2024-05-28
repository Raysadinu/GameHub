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
        /* Modal styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 100px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.9);
        }

        .modal-content {
            margin: auto;
            display: block;
            width: 80%;
            max-width: 1000px; /* Increased max-width */
        }

        .modal-content, #caption {
            animation-name: zoom;
            animation-duration: 0.6s;
        }

        @keyframes zoom {
            from {transform:scale(0)}
            to {transform:scale(1)}
        }

        .close {
            position: absolute;
            top: 50px;
            right: 50px;
            color: #fff;
            font-size: 40px;
            font-weight: bold;
            transition: 0.3s;
        }

        .close:hover,
        .close:focus {
            color: #bbb;
            text-decoration: none;
            cursor: pointer;
        }

        .slideshow-container {
            position: relative;
            max-width: 100%;
            margin: auto;
        }

        .slides {
            display: none;
            width: 100%;
        }

        .prev, .next {
            cursor: pointer;
            position: absolute;
            top: 50%;
            width: auto;
            padding: 16px;
            margin-top: -22px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            transition: 0.6s ease;
            border-radius: 0 3px 3px 0;
            user-select: none;
        }

        .next {
            right: 0;
            border-radius: 3px 0 0 3px;
        }

        .prev {
            left: 0;
            border-radius: 3px 0 0 3px;
        }

        .prev:hover, .next:hover {
            background-color: rgba(0,0,0,0.8);
        }

        .thumbnail-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        .thumbnail {
            cursor: pointer;
            width: 100px;
            height: 60px;
            margin: 0 5px;
            transition: 0.3s;
        }

        .thumbnail:hover {
            opacity: 0.8;
        }

        .thumbnail.active-thumb {
            border: 2px solid #717171;
        }
    </style>


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
