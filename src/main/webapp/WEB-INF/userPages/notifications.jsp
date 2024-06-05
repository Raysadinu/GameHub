<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Profile">

    <div class="container text-center" style="margin-top: 100px">
        <h1>Your notifications</h1>

        <div class="col-md-8 offset-md-2">
            <h4 class="mb-4">Notifications</h4>
            <p class="unread-count mb-3">You have ${unreadNotificationsCount} unread notifications</p>
            <div class="list-group notifications-section" style="max-height: 400px; overflow-y: auto; border: 1px solid #dee2e6; border-radius: 10px; padding: 20px; background-color: #f8f9fa;">
                <!-- Unread Notifications -->
                <c:forEach var="notification" items="${unreadNotifications}">
                    <a href="${pageContext.request.contextPath}/ViewNotification?notificationId=${notification.id}" class="list-group-item list-group-item-action list-group-item-warning notification-item mb-3">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1 text-dark">${notification.message}</h5>
                            <small class="text-muted">${notification.formattedCreatedAt}</small>
                        </div>
                    </a>
                </c:forEach>

                <!-- Read Notifications -->
                <c:forEach var="notification" items="${readNotifications}">
                    <a href="${pageContext.request.contextPath}/ViewNotification?notificationId=${notification.id}" class="list-group-item list-group-item-action notification-item mb-3">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1 text-dark">${notification.message}</h5>
                            <small class="text-muted">${notification.formattedCreatedAt}</small>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</t:template>
