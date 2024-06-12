package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Games.GameDto;
import com.gamehub2.gamehub.dto.Others.LibraryDto;
import com.gamehub2.gamehub.dto.Others.PostDto;
import com.gamehub2.gamehub.dto.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Other.FollowBean;
import com.gamehub2.gamehub.ejb.Other.LibraryBean;
import com.gamehub2.gamehub.ejb.Other.NotificationBean;
import com.gamehub2.gamehub.ejb.Other.PostBean;
import com.gamehub2.gamehub.ejb.Users.UserBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "Profile", value = "/Profile")
public class Profile extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @Inject
    UserDetailsBean userDetailsBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered Profile.doGet " + request.getParameter("username") + " **\n");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = request.getParameter("username");

        List<UserDetailsDto> allUserDetails = userDetailsBean.findAllUserDetails();
        UserDetailsDto thisUser = userDetailsBean.getUserDetailsByUsername(user.getUsername(), allUserDetails);

        request.setAttribute("user", thisUser);

        LOG.info("\n** Exited Profile.doGet ... **\n");
        request.getRequestDispatcher("/WEB-INF/userPages/profile.jsp").forward(request, response);
    }
}