<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:community pageTitle="Community">
    <div class="container text-center" style="margin-top: 100px">
        <div>
            <c:forEach var="post" items="${postList}">
                <div class="post">
                    <div class="post-info">
                        <c:set var="profilePicture" value="${userPicturesMap[post.user.username]}"/>
                        <c:if test="${not empty profilePicture}">
                            <img src="data:image/${profilePicture.imageFormat};base64,${profilePicture.base64ImageData}" alt="Profile Picture" class="profile-picture">
                        </c:if>
                        <c:choose>
                            <c:when test="${post.user.username == sessionScope.user.username}">
                                <a href="${pageContext.request.contextPath}/Profile" style="text-decoration: none; color: #333; font-size: 18px;"><strong>${post.user.username}</strong></a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/OtherProfile?username=${post.user.username}" style="text-decoration: none; color: #333; font-size: 18px;"><strong>${post.user.username}</strong></a>
                            </c:otherwise>
                        </c:choose>

                        <p class="post-info"><fmt:formatDate value="${post.postingDate}" pattern="yyyy-MM-dd HH:mm" /></p>
                        <c:if test="${not empty post.game}">
                            <p class="post-info">${post.game.gameName}</p>
                        </c:if>
                    </div>
                    <div class="post-content">
                        <p>${post.description}</p>
                        <div class="slideshow-container" id="slideshow-${post.postId}">
                            <div class="large-image-container">
                                <img id="largeImage-${post.postId}" src="data:image/${post.postPictures[0].imageFormat};base64,${post.postPictures[0].base64ImageData}" style="width:700px;height:415px;">
                            </div>
                            <div class="thumbnail-container">
                                <c:forEach var="picture" items="${post.postPictures}" varStatus="status">
                                    <img class="thumbnail-${post.postId}" src="data:image/${picture.imageFormat};base64,${picture.base64ImageData}" style="width:100px;height:60px;cursor:pointer;margin:5px;" onclick="changeImage('${post.postId}', '${picture.imageFormat}', '${picture.base64ImageData}')">
                                </c:forEach>
                            </div>
                        </div>
                        <div id="myModal" class="modal">
                            <span class="close" onclick="closeModal()">&times;</span>
                            <img class="modal-content" id="img01">
                            <div id="caption"></div>
                        </div>
                    </div>
                    <div class="reactions">
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
                                    <c:when test="${reaction.reactionType eq 'DISLIKE'}">
                                        <c:set var="userReactedDislike" value="true"/>
                                    </c:when>
                                    <c:when test="${reaction.reactionType eq 'FUN'}">
                                        <c:set var="userReactedFun" value="true"/>
                                    </c:when>
                                    <c:when test="${reaction.reactionType eq 'HELPFUL'}">
                                        <c:set var="userReactedHelpful" value="true"/>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${userReactedLike}">
                                <button disabled>&#128077;</button>
                            </c:when>
                            <c:otherwise>
                                <button class="reaction-button ${userReactedLike ? 'reacted' : ''}" onclick="reactPost('${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=like')">&#128077;</button>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${userReactedDislike}">
                                <button disabled>&#128078;</button>
                            </c:when>
                            <c:otherwise>
                                <button class="reaction-button ${userReactedDislike ? 'reacted-dislike' : ''}" onclick="reactPost('${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=dislike')">&#128078;</button>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${userReactedFun}">
                                <button disabled>&#128514;</button>
                            </c:when>
                            <c:otherwise>
                                <button class="reaction-button ${userReactedFun ? 'reacted-fun' : ''}" onclick="reactPost('${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=fun')">&#128514;</button>
                            </c:otherwise>
                        </c:choose>
                        <button class="reaction-button ${userReactedHelpful ? 'reacted-helpful' : ''}" onclick="reactPost('${pageContext.request.contextPath}/PostReact?postId=${post.postId}&reaction=helpful')" title="Good Info"><i class="bi bi-info-circle-fill" style="color: ${userReactedHelpful ? 'green' : '#333'}"></i></button>
                    </div>
                    <div class="action-buttons">
                        <a href="${pageContext.request.contextPath}/ViewPost?postId=${post.postId}" class="btn btn-sm btn-primary">View Post</a>
                        <c:if test="${post.user.username eq user.username}">
                            <form action="${pageContext.request.contextPath}/DeletePost" method="post">
                                <input type="hidden" name="postId" value="${post.postId}">
                                <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</t:community>

<script>
    function changeImage(postId, imageFormat, base64ImageData) {
        let largeImage = document.getElementById("largeImage-" + postId);
        largeImage.src = "data:image/" + imageFormat + ";base64," + base64ImageData;
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

    function reactPost(url) {
        // Perform AJAX request to react to the post
        window.location.href = url;
    }
</script>
