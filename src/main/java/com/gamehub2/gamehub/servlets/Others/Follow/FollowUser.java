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


@WebServlet(name = "FollowUser", value = "/FollowUser")
public class FollowUser extends HttpServlet {

    @Inject
    FollowBean followBean;
    private static final Logger LOG = Logger.getLogger(FollowUser.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User followerUser = (User) session.getAttribute("user");
        String followedUser = request.getParameter("followed");


        followBean.addFollow(followerUser.getUsername(), followedUser);
        response.sendRedirect(request.getContextPath()+"/OtherProfile?username=" + followedUser);

    }
}