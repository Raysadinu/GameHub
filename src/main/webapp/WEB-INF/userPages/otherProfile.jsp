<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Profile">

    <div class="container text-center">

        <h1>Profile, ${user.username}</h1>
        <c:choose>
            <c:when test="${isFollower}">
                <form action="${pageContext.request.contextPath}/UnfollowUser" method="post">
                    <input type="hidden" name="followed" value="${user.username}" />
                    <input type="submit" value="Unfollow" class="btn btn-danger"/>
                    <input type="hidden" name="fromPage" value="profile" />
                </form>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/FollowUser" method="post">
                    <input type="hidden" name="followed" value="${user.username}" />
                    <input type="submit" value="Follow" class="btn btn-primary"/>
                </form>
            </c:otherwise>
        </c:choose>
        <p>Full name: ${user.firstName} ${user.lastName}</p>
        <p>Birthday: ${user.birthDate}</p>
        <p>Phone number: ${user.phoneNumber}</p>
        <p>Bio: ${user.bio}</p>
    </div>
</t:template>
