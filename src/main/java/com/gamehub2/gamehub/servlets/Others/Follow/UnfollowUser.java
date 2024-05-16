package com.gamehub2.gamehub.servlets.Others.Follow;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.FollowBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.logging.Logger;


@WebServlet(name = "UnfollowUser", value = "/UnfollowUser")
public class UnfollowUser extends HttpServlet {

    @Inject
    FollowBean followBean;
    private static final Logger LOG = Logger.getLogger(UnfollowUser.class.getName());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User  followerUser  = (User) session.getAttribute("user");
        String followedUser  = request.getParameter("followed");

        String fromPage = request.getParameter("fromPage");

        LOG.info("Unfollowing user: " + followedUser + " by user: " + followerUser.getUsername());

        followBean.removeFollow(followerUser.getUsername(), followedUser);

        LOG.info("Unfollowed user: " + followedUser + " by user: " + followerUser.getUsername());

        if ("profile".equals(fromPage)) {
            LOG.info("Redirecting to OtherProfile page for user: " + followedUser);
            response.sendRedirect(request.getContextPath() + "/OtherProfile?username=" + followedUser);
        } else if ("following".equals(fromPage)) {
            LOG.info("Redirecting to Following page for user: " + followerUser.getUsername());
            response.sendRedirect(request.getContextPath() + "/Following?username=" + followerUser.getUsername());
        } else {
            LOG.warning("Unknown value for 'fromPage' parameter: " + fromPage);
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }
}