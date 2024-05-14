<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/14/2024
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Admins">
    <div class="container text-center">
        <h1>Admins</h1>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/AddAdmin">Add Admin</a>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Username</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="admin" items="${admins}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${admin.username}</td>
                    <td>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/DeleteAdmin?username=${admin.username}">Delete Admin</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:template>