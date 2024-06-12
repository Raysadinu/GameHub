<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:generalTemplate pageTitle="Login">
    <a class="navbar-brand" href="#">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/icon.png" alt="GameHub Logo" height="250" class="center-image">
        </div>
    </a>
    <div class="container">
            <h1>Login to Your Account</h1>

            <c:if test="${message != null}">
                <div class="alert alert-warning" role="alert">
                        ${message}
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/Login" method="post">
                <div>
                    <label for="username"></label>
                    <input type="text" id="username" placeholder="Username" name="j_username" value="${not empty j_username ? j_username : ''}" required>
                </div>
                <div>
                    <label for="password"></label>
                    <input type="password" id="password"  placeholder="Password" name="j_password" required>
                </div>
                <button type="submit">Login</button>
            </form>
            <p>Don't have an account? <a href="${pageContext.request.contextPath}/Register">Register here</a></p>
    </div>
</t:generalTemplate>



