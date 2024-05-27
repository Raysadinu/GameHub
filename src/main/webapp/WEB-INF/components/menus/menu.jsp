<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="userRole" value="${sessionScope.isAdmin == true ? 'Admin' : 'User'}" />

<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">

            <div class="navbar-collapse justify-content-start">

                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Home">Store</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/Profile"> ${sessionScope.user.username} </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Library">Library</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Community">Community</a>
                    </li>

                </ul>
            </div>

            <a class="navbar-brand mx-auto" href="#">GameHub</a>

            <div class="navbar-collapse justify-content-end">

                <ul class="navbar-nav mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Wishlist">Wishlist</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Cart">Cart</a>
                    </li>
                    <li class="nav-item dropdown">
                        <c:if test="${userRole eq 'Admin'}">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAdmin" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Admin Settings
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownAdmin">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Admins">Admins</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Users">Users</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Games">Games</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PaymentRequests">Payment requests</a></li>
                            </ul>
                        </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Log out</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
