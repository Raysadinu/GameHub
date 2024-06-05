<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:template pageTitle="Community">
    <div class="container text-center" style="margin-top: 100px">
        <div>
            <c:forEach var="post" items="${postList}">

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

                <div class="reaction-buttons">
                    <c:set var="userReactedLike" value="false"/>
                    <c:set var="userReactedFun" value="false"/>
                    <c:set var="userReactedHelpful" value="false"/>
                    <c:set var="userReactedDislike" value="false"/>
                    <c:forEach var="reaction" items="${post.reactions}">
                        <c:if test="${reaction.user.username eq sessionScope.user.username}">
                            <c:choose>
                                <c:when test="${reaction.reactionType eq 'LIKE'}">
                                    <c:set var="userReactedLike" value="true"/>
                                </c:when>
                                <c:when test="${reaction.reactionType eq 'FUN'}">
                                    <c:set var="userReactedFun" value="true"/>
                                </c:when>
                                <c:when test="${reaction.reactionType eq 'HELPFUL'}">
                                    <c:set var="userReactedHelpful" value="true"/>
                                </c:when>
                                <c:when test="${reaction.reactionType eq 'DISLIKE'}">
                                    <c:set var="userReactedDislike" value="true"/>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${userReactedLike}">
                            <button class="btn-link" disabled>&#128077;</button>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=like" class="btn-link">&#128077;</a>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${userReactedFun}">
                            <button class="btn-link" disabled  style="color:green">&#128514;</button>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=fun" class="btn-link">&#128514;</a>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${userReactedHelpful}">
                            <button class="btn-link" disabled style="color: green;">Helpful</button>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=helpful" class="btn-link" style="color: green;">Helpful</a>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${userReactedDislike}">
                            <button class="btn-link" disabled style="color:red">&#128078;</button>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=dislike" class="btn-link">&#128078;</a>
                        </c:otherwise>
                    </c:choose>
                    <a href="${pageContext.request.contextPath}/ViewPost?postId=${post.postId}" class="btn btn-primary">View Post</a>

                    <c:if test="${post.user.username eq user.username}">
                        <form action="${pageContext.request.contextPath}/DeletePost" method="post">
                            <input type="hidden" name="postId" value="${post.postId}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </c:if>
            </c:forEach>
        </div>
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
