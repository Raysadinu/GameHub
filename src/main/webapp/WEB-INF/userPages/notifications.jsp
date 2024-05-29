<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template pageTitle="Profile">

    <div class="container text-center" style="margin-top: 100px">
    <h1>Your notifications</h1>

        <div class="col-md-3">
            <h4>Notifications</h4>
            <p>You have ${unreadNotificationsCount} unread notifications</p>
            <div class="list-group notifications-section" style="max-height: 400px; overflow-y: scroll;">
                <!-- Unread Notifications -->
                <c:forEach var="notification" items="${unreadNotifications}">
                    <a href="${pageContext.request.contextPath}/ViewNotification?notificationId=${notification.id}" class="list-group-item list-group-item-action list-group-item-warning notification-item">
                            ${notification.message} <br> <small>${notification.formattedCreatedAt}</small>
                    </a>
                </c:forEach>

                <!-- Read Notifications -->
                <c:forEach var="notification" items="${readNotifications}">
                    <a href="${pageContext.request.contextPath}/ViewNotification?notificationId=${notification.id}" class="list-group-item list-group-item-action notification-item">
                            ${notification.message} <br> <small>${notification.formattedCreatedAt}</small>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
</t:template>
