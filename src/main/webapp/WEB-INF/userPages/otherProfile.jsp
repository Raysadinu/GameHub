<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="User Profile">

    <div class="container text-center"  style="margin-top: 100px">
        <c:if test="${not empty userDetails.profilePicture}">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <c:if test="${not empty userDetails.profilePicture}">
                        <img src="data:image/${userDetails.profilePicture.imageFormat};base64,${userDetails.profilePicture.base64ImageData}" alt="Profile Picture" width="200" height="200">
                    </c:if>
                    <c:if test="${empty userDetails.profilePicture}">
                        <img src="img/default-picture.jpg" alt="Default Profile Picture" width="200" height="200">
                    </c:if>
                </div>
            </div>
        </c:if>
        <h1>${userDetails.username}</h1>
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
        <p>Full name: ${userDetails.firstName} ${userDetails.lastName}</p>
        <p>Birthday: ${userDetails.birthDate}</p>
        <p>Phone number: ${userDetails.phoneNumber}</p>
        <p>Bio: ${userDetails.bio}</p>
    </div>
</t:template>
