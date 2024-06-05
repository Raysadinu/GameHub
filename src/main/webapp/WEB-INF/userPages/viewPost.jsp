<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:template pageTitle="Community">
    <style>
        .profile-picture {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            object-fit: cover;
        }
        .comment .profile-picture {
            width: 30px;
            height: 30px;
        }
        .comment {
            margin-top: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
    <div class="container text-center" style="margin-top: 100px">
        <div>
            <c:if test="${not empty userProfilePicture}">
                <img class="profile-picture" src="data:image/${userProfilePicture.imageFormat};base64,${userProfilePicture.base64ImageData}" alt="Profile Picture">
            </c:if>
            <c:choose>
                <c:when test="${post.user.username == sessionScope.user.username}">
                    <a href="${pageContext.request.contextPath}/Profile">${post.user.username}</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/OtherProfile?username=${post.user.username}">${post.user.username}</a>
                </c:otherwise>
            </c:choose>
            <p><fmt:formatDate value="${post.postingDate}" pattern="yyyy-MM-dd HH:mm" /></p>
            <c:if test="${not empty post.game}">
                <p>${post.game.gameName}</p>
            </c:if>
            <p>${post.description}</p>

            <c:choose>
                <c:when test="${fn:length(post.postPictures) == 1}">
                    <!-- Display single large image -->
                    <div class="single-image">
                        <img src="data:image/${post.postPictures[0].imageFormat};base64,${post.postPictures[0].base64ImageData}" alt="Post Picture" style="width:700px;height:415px;" onclick="openModal(this)">
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="slideshow-container">
                        <c:forEach var="picture" items="${post.postPictures}" varStatus="status">
                            <div class="slides ${status.index == 0 ? 'active' : ''}">
                                <img src="data:image/${picture.imageFormat};base64,${picture.base64ImageData}" alt="Post Picture" style="width:700px;height:415px;" onclick="openModal(this)">
                            </div>
                        </c:forEach>
                    </div>
                    <br>
                    <div class="thumbnail-container">
                        <c:forEach var="picture" items="${post.postPictures}" varStatus="status">
                            <img class="thumbnail ${status.index == 1 ? 'active-thumb' : ''}" src="data:image/${picture.imageFormat};base64,${picture.base64ImageData}" onclick="currentSlide(${status.index + 1})">
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
            <div id="myModal" class="modal">
                <span class="close" onclick="closeModal()">&times;</span>
                <img class="modal-content" id="img01">
                <div id="caption"></div>
            </div>


            <div id="reactionsDropdown" class="dropdown-content">
                <ul class="reactions-list">
                    <c:forEach var="reaction" items="${reactions}">
                        <li>
                            <c:if test="${not empty userPicturesMap[reaction.user.username]}">
                                <img class="profile-picture" src="data:image/${userPicturesMap[reaction.user.username].imageFormat};base64,${userPicturesMap[reaction.user.username].base64ImageData}" alt="User's Profile Picture">
                            </c:if>
                                ${reaction.user.username} reacted with
                            <c:choose>
                                <c:when test="${reaction.reactionType eq 'LIKE'}">
                                    &#128077;
                                </c:when>
                                <c:when test="${reaction.reactionType eq 'FUN'}">
                                    &#128514;
                                </c:when>
                                <c:when test="${reaction.reactionType eq 'HELPFUL'}">
                                    <p style="color: green;">Helpful</p>
                                </c:when>
                                <c:when test="${reaction.reactionType eq 'DISLIKE'}">
                                    &#128078;
                                </c:when>
                            </c:choose>
                        </li>
                    </c:forEach>
                    <c:if test="${empty reactions}">
                        <li>No reactions yet.</li>
                    </c:if>
                </ul>
            </div>
            <c:if test="${not empty comments}">
                <div class="comments-section">
                    <h4>Comments:</h4>
                    <c:forEach var="comment" items="${comments}">
                        <div class="comment">

                            <c:if test="${not empty userPicturesMap[comment.user.username]}">
                                <img class="profile-picture" src="data:image/${userPicturesMap[comment.user.username].imageFormat};base64,${userPicturesMap[comment.user.username].base64ImageData}" alt="User's Profile Picture">
                            </c:if>
                            <p><strong>${comment.user.username}</strong> commented on  ${comment.formattedPostedAt}</p>
                            <p>${comment.content}</p>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/PostComment" method="post" class="comment-form">
                <div class="form-group">
                    <label for="comment">Your Comment:</label><br>
                    <textarea id="comment" name="commentContent" rows="4" cols="50" placeholder="Add your comment here" class="form-control"></textarea>
                </div>
                <input type="hidden" name="postId" value="${post.postId}">
                <input type="submit" value="Post Comment" class="btn btn-primary">
            </form>

        </div>
    <script>
        let slideIndex = 1;

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            let slides = document.getElementsByClassName("slides");
            let thumbnails = document.getElementsByClassName("thumbnail");
            if (slides.length > 1) {
                let i;
                if (n > slides.length) { slideIndex = 1 }
                if (n < 1) { slideIndex = slides.length }
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                for (i = 0; i < thumbnails.length; i++) {
                    thumbnails[i].className = thumbnails[i].className.replace(" active-thumb", "");
                }
                slides[slideIndex - 1].style.display = "block";
                thumbnails[slideIndex - 1].className += " active-thumb";
            }
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

        <c:if test="${fn:length(post.postPictures) > 1}">
        showSlides(slideIndex);
        </c:if>
    </script>
</t:template>
