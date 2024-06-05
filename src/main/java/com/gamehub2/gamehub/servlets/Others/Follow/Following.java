package com.gamehub2.gamehub.servlets.Others.Follow;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Others.FollowDto;
import com.gamehub2.gamehub.ejb.Other.FollowBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "Following", value = "/Following")
public class Following extends HttpServlet {

    @Inject
    FollowBean followBean;
    private static final Logger LOG = Logger.getLogger(Following.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        List<FollowDto> allFollowings =followBean.findAllFollows();
        List<FollowDto> userFollowings=followBean.findFollowsByUser(currentUser.getUsername(),allFollowings);
        request.setAttribute("user",currentUser);
        request.setAttribute("followings", userFollowings);
        request.getRequestDispatcher("/WEB-INF/userPages/following.jsp").forward(request, response);
    }
}