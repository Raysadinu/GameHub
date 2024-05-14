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


@WebServlet(name = "FollowUser", value = "/FollowUser")
public class FollowUser extends HttpServlet {

    @Inject
    FollowBean followBean;
    private static final Logger LOG = Logger.getLogger(FollowUser.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String followedUser = request.getParameter("followed");
        String followerUser =request.getRemoteUser();

        followBean.addFollow(followerUser, followedUser);
        response.sendRedirect(request.getContextPath()+"/OtherProfile?username=" + followedUser);

    }
}