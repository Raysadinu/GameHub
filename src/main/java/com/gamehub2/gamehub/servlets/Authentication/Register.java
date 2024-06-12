package com.gamehub2.gamehub.servlets.Authentication;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Users.AuthenticationBean;
import com.gamehub2.gamehub.ejb.Users.UserBean;
import com.gamehub2.gamehub.entities.Users.UserDetails;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Register.class.getName());
    @Inject
    UserBean userBean;
    @Inject
    AuthenticationBean authenticationBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/components/forms/register.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("\n Entered Register.doPost method \n");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String confirmPassword = request.getParameter("confirmPassword");

        List<String> existingUsernames = userBean.getExistingUsernames();
        if(password.equals(confirmPassword)) {
            password=authenticationBean.encryptPassword(password);
        } else{
            LOG.info("\n Password mismatch! Exiting Register.doPost method. \n");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("errorMessage", "Password mismatch, please make sure fill in the same password in both password fields.");
            request.getRequestDispatcher("/WEB-INF/components/forms/register.jsp").forward(request, response);
        }

        if(!existingUsernames.contains(username)) {
            userBean.createUser(username, email, password);
            LOG.info("\n Created user " + username + " successfully. Exiting Register.doPost method. \n");
            response.sendRedirect(request.getContextPath() + "/Login");
        }else{
            LOG.info("\n User already exists. Exiting Register.doPost method. \n");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("errorMessage", "Username already exists. Please provide a new username...");
            request.getRequestDispatcher("/WEB-INF/components/forms/register.jsp").forward(request, response);
        }
    }
}

