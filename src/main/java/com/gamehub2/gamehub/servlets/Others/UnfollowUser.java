package com.gamehub2.gamehub.servlets.Others;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.FollowBean;
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

        followBean.removeFollow(followerUser.getUsername(), followedUser);


        if ("profile".equals(fromPage)) {
            response.sendRedirect(request.getContextPath() + "/Profile?username=" + followedUser);
        } else if ("friends".equals(fromPage)) {
            response.sendRedirect(request.getContextPath() + "/Friends?username=" + followedUser);
        } else {

            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }
}