<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="userRole" value="${sessionScope.isAdmin == true ? 'Admin' : 'User'}" />


<header>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/menu.css">
    <nav class="navbar navbar-expand-md fixed-top">
        <div class="container-fluid">

            <div class="navbar-collapse justify-content-start">

                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Home">Store</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Library">Library</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/CommunityPost">Community</a>
                    </li>

                </ul>
            </div>

            <a class="navbar-brand" href="#">
                <div class="logo-container">
                    <img src="${pageContext.request.contextPath}/resources/icon.png" alt="GameHub Logo" height="100">
                </div>
            </a>

            <div class="navbar-collapse justify-content-end">

                <ul class="navbar-nav mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Profile"> ${sessionScope.user.username} </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Notifications"><i class="far fa-bell"></i></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Wishlist"><i class="far fa-heart"></i></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Cart"><i class="bi bi-cart3"></i></a>
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
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Log out</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
