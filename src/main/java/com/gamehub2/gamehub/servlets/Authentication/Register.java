package com.gamehub2.gamehub.servlets.Authentication;

import java.io.IOException;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("\n Entered Register.doGet method \n");
        request.setAttribute("userGroups", new String[] {"USER","ADMIN"});
        LOG.info("\n Exited Register.doGet method, redirecting to register.jsp \n");
        request.getRequestDispatcher("/WEB-INF/components/forms/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("\n Entered Register.doPost method \n");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String[] userGroups = request.getParameterValues("user_groups");
        if (userGroups == null) {
            userGroups = new String[0];
        }
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(username);
        userBean.createUser(username, email, password, Arrays.asList(userGroups),userDetails);

        request.login(username, password);
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}
