<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">

            <div class="navbar-collapse justify-content-start">

                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <c:if test="${pageContext.request.isUserInRole('USER') || pageContext.request.isUserInRole('ADMIN')}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Home">Store</a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${pageContext.request.isUserInRole('USER') || pageContext.request.isUserInRole('ADMIN')}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Profile">Profile</a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${pageContext.request.isUserInRole('USER') || pageContext.request.isUserInRole('ADMIN')}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Friends">Friends</a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${pageContext.request.isUserInRole('USER') || pageContext.request.isUserInRole('ADMIN')}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Library">Library</a>
                        </c:if>
                    </li>
                </ul>
            </div>

            <a class="navbar-brand mx-auto" href="#">GameHub</a>

            <div class="navbar-collapse justify-content-end">

                <ul class="navbar-nav mb-2 mb-md-0">
                    <li class="nav-item">
                        <c:if test="${pageContext.request.isUserInRole('USER') || pageContext.request.isUserInRole('ADMIN')}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Wishlist">Wishlist</a>
                        </c:if>
                    </li>
                    <li class="nav-item">
                        <c:if test="${pageContext.request.isUserInRole('USER') || pageContext.request.isUserInRole('ADMIN')}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Cart">Cart</a>
                        </c:if>
                    </li>
                    <li class="nav-item dropdown">
                        <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
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
                        <c:choose>
                            <c:when test="${pageContext.request.getRemoteUser() == null}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
