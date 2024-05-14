package com.gamehub2.gamehub.servlets.Authentication;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Admins.AdminBean;
import com.gamehub2.gamehub.ejb.Users.AuthenticationBean;
import com.gamehub2.gamehub.ejb.Users.UserBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Inject
    UserBean userBean;

    @Inject
    AuthenticationBean authenticationBean;
    @Inject
    AdminBean adminBean;
    private static final Logger LOG = Logger.getLogger(Login.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is already authenticated and if so, redirect them to the home page

        if (request.getSession().getAttribute("username") != null) {
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n Entered Login.doPost method \n");

        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        User user = authenticationBean.login(username, password);

        boolean isAdmin = adminBean.isAdmin(username);

        if (user != null) {
            // Authentication successful, store user in session
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("isAdmin", isAdmin);
            LOG.info("User logged in - Username: " + user.getUsername() + ", Role: " + user.getRole());
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            LOG.info("\n Authentication failed -> credentials invalid (returning to login) \n");
            request.setAttribute("message", "Username or password incorrect");
            request.setAttribute("j_username", username);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}