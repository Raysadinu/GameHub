<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/14/2024
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template pageTitle="Add Admin">
    <div class="container text-center">
        <h1>Add Admin</h1>
            <%-- Check if there's an alert message --%>
        <c:if test="${not empty alertMessage}">
            <script>alert("${alertMessage}");</script>
        </c:if>
        <form id="addAdminForm" action="${pageContext.request.contextPath}/AddAdmin" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Admin</button>
        </form>
    </div>
</t:template>