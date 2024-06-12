package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Others.NotificationDto;
import com.gamehub2.gamehub.ejb.Other.NotificationBean;
import com.gamehub2.gamehub.entities.Others.Notification;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.logging.Logger;

@WebServlet(name = "ViewNotification", value = "/ViewNotification")
public class ViewNotification extends HttpServlet {

    private static final Logger log = Logger.getLogger(ViewNotification.class.getName());

    @Inject
    NotificationBean notificationBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String notificationIdString = req.getParameter("notificationId");
        Long notificationId = Long.parseLong(notificationIdString);
        NotificationDto thisNotification = notificationBean.findNotificationById(notificationId);
        if (thisNotification != null) {
            if (thisNotification.getType() == Notification.NotificationType.FOLLOW) {
                notificationBean.markNotificationAsSeen(notificationId);
            }
            if (thisNotification.getType() == Notification.NotificationType.REACTION_TO_POST ||
                    thisNotification.getType() == Notification.NotificationType.COMMENT_RECEIVED) {
                notificationBean.markNotificationAsSeen(notificationId);
                resp.sendRedirect(req.getContextPath() + "/ViewPost?postId=" + thisNotification.getPost().getPostId());
            }
            if (thisNotification.getType() == Notification.NotificationType.SALE) {
                notificationBean.markNotificationAsSeen(notificationId);
                resp.sendRedirect(req.getContextPath() + "/Wishlist");
            }
            if (thisNotification.getType() == Notification.NotificationType.PAYMENT_STATUS) {
                notificationBean.markNotificationAsSeen(notificationId);
            }
        }
    }

}