<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

            <c:forEach var="picture" items="${post.postPictures}">
                <c:if test="${not empty picture}">
                    <img src="data:image/${picture.imageFormat};base64,${picture.base64ImageData}" alt="Post Picture" width="700" height="415" />
                </c:if>
            </c:forEach>
            <!-- Dropdown content -->
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
        function toggleDropdown() {
            var dropdownContent = document.getElementById("reactionsDropdown");
            dropdownContent.classList.toggle("show");
        }

        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.matches('.dropdown-btn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                for (var i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
    </script>
</t:template>
