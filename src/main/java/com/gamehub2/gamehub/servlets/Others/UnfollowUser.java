package com.gamehub2.gamehub.servlets.Others;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.FollowBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;


@WebServlet(name = "UnfollowUser", value = "/UnfollowUser")
public class UnfollowUser extends HttpServlet {

    @Inject
    FollowBean followBean;
    private static final Logger LOG = Logger.getLogger(UnfollowUser.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String followedUser  = request.getParameter("followed");
        String followerUser = request.getRemoteUser();
        String fromPage = request.getParameter("fromPage");

        followBean.removeFollow(followerUser, followedUser);


        if ("profile".equals(fromPage)) {
            response.sendRedirect(request.getContextPath() + "/Profile?username=" + followedUser);
        } else if ("friends".equals(fromPage)) {
            response.sendRedirect(request.getContextPath() + "/Friends?username=" + followedUser);
        } else {

            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }
}