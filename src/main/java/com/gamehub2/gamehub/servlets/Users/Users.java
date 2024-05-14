package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.common.Admins.AdminDto;
import com.gamehub2.gamehub.common.Users.UserDto;
import com.gamehub2.gamehub.ejb.Admins.AdminBean;
import com.gamehub2.gamehub.ejb.Users.UserBean;
import com.gamehub2.gamehub.entities.Users.User;
import com.gamehub2.gamehub.servlets.Games.Games;
import jakarta.annotation.security.DeclareRoles;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {
    @Inject
    UserBean userBean;
    @Inject
    AdminBean adminBean;
    private static final Logger LOG = Logger.getLogger(Games.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("Entering doGet method of Users servlet");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        boolean isAdmin = adminBean.isAdmin(user.getUsername());
        request.setAttribute("isAdmin", isAdmin);

        try {
            List<UserDto> users = userBean.findAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/userPages/users.jsp").forward(request, response);
        } catch (Exception e) {
            // Log any exceptions that occur
            LOG.severe("An error occurred while processing doGet: " + e.getMessage());
            // Optionally, you can rethrow the exception or handle it differently based on your application's requirements
            throw new ServletException("Error processing doGet", e);
        } finally {
            // Log exit
            LOG.info("Exiting doGet method of Users servlet");
        }
    }

}