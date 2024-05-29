package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.common.Others.NotificationDto;
import com.gamehub2.gamehub.common.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Other.NotificationBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@WebServlet(name = "Notifications", value = "/Notifications")
public class Notifications extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(OtherProfile.class.getName());

    @Inject
    private NotificationBean notificationBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Entered Profile.doGet");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<NotificationDto> readNotifications = notificationBean.findReadNotificationsByUsername(user.getUsername());
        List<NotificationDto> unreadNotifications = notificationBean.findUnreadNotificationsByUsername(user.getUsername());

        int unreadNotificationsCount = unreadNotifications.size();
        request.setAttribute("unreadNotificationsCount", unreadNotificationsCount);

        request.setAttribute("readNotifications", readNotifications);
        request.setAttribute("unreadNotifications", unreadNotifications);
        request.getRequestDispatcher("/WEB-INF/userPages/notifications.jsp").forward(request, response);
    }
}
