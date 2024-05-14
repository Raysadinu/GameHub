package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.common.Users.UserDto;
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

import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "SettingsUser", value = "/SettingsUser")
public class SettingsUser extends HttpServlet {

    @Inject
    UserBean userBean;
    private static final Logger LOG = Logger.getLogger(SettingsUser.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Entered SettingsUser.doGet method");

        String username = request.getParameter("username");
        List<UserDto> userList = userBean.findAllUsers();
        UserDto selectedUser = userBean.findUserByUsername(username, userList);
        request.setAttribute("user", selectedUser);
        request.getRequestDispatcher("/WEB-INF/userPages/settings.jsp").forward(request, response);
    }

}