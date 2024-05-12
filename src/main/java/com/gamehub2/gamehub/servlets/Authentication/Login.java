package com.gamehub2.gamehub.servlets.Authentication;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Login.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is already authenticated and if so, redirect them to the home page
        if (request.getSession().getAttribute("username") != null) {
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            request.getRequestDispatcher("/WEB-INF/components/forms/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Simulate credential validation - replace this part with actual credential validation
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isValid(username, password)) {
            // If credentials are valid, store the username in the session
            request.getSession().setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/Home");
        } else {
            // If credentials are not valid, display an error message
            request.setAttribute("message", "Username or password incorrect");
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        }
    }
    private boolean isValid(String username, String password) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }
}