package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.common.Users.UserDto;
import com.gamehub2.gamehub.ejb.Users.UserBean;
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

import java.util.List;
import java.util.logging.Logger;

@DeclareRoles({"ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}))
@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {
    @Inject
    UserBean userBean;
    private static final Logger LOG = Logger.getLogger(Games.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log entry
        LOG.info("Entering doGet method of Users servlet");

        try {
            List<UserDto> users = userBean.findAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/adminPages/users.jsp").forward(request, response);
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