<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:generalTemplate pageTitle="Register">

    <a class="navbar-brand" href="#">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/icon.png" alt="GameHub Logo" height="250" class="center-image">
        </div>
    </a>
    <div class="container">
        <h1>Create Your Account</h1>

        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/Register" method="post">
            <div>
                <label for="email"></label>
                <input type="email" id="email" placeholder="Email" name="email" value="${empty email ? '' : email}" required>
            </div>
            <div>
                <label for="username"></label>
                <input type="text" id="username" placeholder="Username" name="username" value="${empty username ? '' : username}" required>
            </div>
            <div>
                <label for="password"></label>
                <input type="password" id="password" placeholder="Password" name="password" value="${empty password ? '' : password}" required>
            </div>
            <div>
                <label for="confirmPassword"></label>
                <input type="password" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword" required>
            </div>
            <button type="submit">Register</button>
        </form>
        <p>Already have an account? <a href="${pageContext.request.contextPath}/Login">Login here</a></p>

    </div>

</t:generalTemplate>
