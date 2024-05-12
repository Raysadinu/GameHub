<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 5/5/2024
  Time: 3:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Profile">

    <div class="container text-center">
        <div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditUser?username=${user.username}">Edit user</a>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditProfile?username=${user.username}">Edit your profile</a>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/Payment?username=${user.username}&page=cards">Cards</a>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/PurchaseHistory?username=${user.username}">Purchase History</a>
        </div>

    </div>
</t:template>

