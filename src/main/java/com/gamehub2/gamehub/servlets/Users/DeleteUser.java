package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Users.UserBean;
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

@WebServlet(name = "DeleteUser", value = "/DeleteUser")
public class DeleteUser extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DeleteUser.class.getName());
    @Inject
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("\n** Entered DeleteUser.doGet method with the username: "+request.getParameter("username")+" to be deleted **\n");

        userBean.deleteUser(request.getParameter("username"));
        response.sendRedirect(request.getContextPath()+"/Users");

        LOG.info("\n** Exited DeleteUser.doGet method. **\n");
    }
}