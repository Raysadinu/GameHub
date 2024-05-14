<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/10/2024
  Time: 9:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Users">
    <div class="container text-center">
        <h1>Users</h1>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Username</th>
                <c:if test="${isAdmin}">
                    <th>Email</th>
                    <th>Actions</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>
                        <c:choose>
                            <c:when test="${pageContext.request.remoteUser == user.username}">
                                <a href="${pageContext.request.contextPath}/Profile">${user.username}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/OtherProfile?username=${user.username}">${user.username}</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${isAdmin}">
                        <td>${user.email}</td>
                        <td>
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/DeleteUser?username=${user.username}">Delete User</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:template>