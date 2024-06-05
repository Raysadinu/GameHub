<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="User Profile">

    <div class="container text-center">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="profile-picture-container">
                    <c:if test="${not empty user.profilePicture}">
                        <img src="data:image/${user.profilePicture.imageFormat};base64,${user.profilePicture.base64ImageData}" alt="Profile Picture" class="profile-picture">
                    </c:if>
                    <c:if test="${empty user.profilePicture}">
                        <img src="default-picture.jpg" alt="Default Profile Picture" class="profile-picture">
                    </c:if>
                </div>
            </div>
        </div>
        <h1 class="mt-4 username">${user.username}<span class="nickname">${user.nickname}</span></h1>

        <c:choose>
            <c:when test="${isFollower}">
                <form action="${pageContext.request.contextPath}/UnfollowUser" method="post">
                    <input type="hidden" name="followed" value="${userDetails.username}" />
                    <input type="submit" value="Unfollow" class="btn btn-danger"/>
                    <input type="hidden" name="fromPage" value="profile" />
                </form>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/FollowUser" method="post">
                    <input type="hidden" name="followed" value="${userDetails.username}" />
                    <input type="submit" value="Follow" class="btn btn-primary"/>
                </form>
            </c:otherwise>
        </c:choose>

        <c:if test="${not empty user.bio}">
            <p class="bio" style="white-space: pre-wrap;">${user.bio}</p>
        </c:if>
    </div>
</t:template>
