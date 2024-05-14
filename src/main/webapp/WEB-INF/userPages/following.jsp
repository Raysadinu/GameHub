<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template pageTitle="Friends">
    <h1>Friends</h1>
    <div class="container text-center">
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Friend</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="following" items="${followings}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${following.followed.username}</td>
                    <td>${following.dateCreated}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/Unfollow" method="post">
                            <input type="hidden" name="friendId" value="${following.followed.username}" />
                            <button type="submit" class="btn btn-danger">Unfollow</button>
                            <input type="hidden" name="fromPage" value="friends" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:template>
