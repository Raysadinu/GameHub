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

@WebServlet(name = "EditUser", value = "/EditUser")
public class EditUser extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditUser.class.getName());
    @Inject
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        LOG.info("Entered EditUser.doGet method for username: " + username);

        List<UserDto> userList = userBean.findAllUsers();
        UserDto selectedUser = userBean.findUserByUsername(username, userList);
        request.setAttribute("user", selectedUser);

        request.getRequestDispatcher("/WEB-INF/userPages/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Entered EditUser.doPost method");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDto newUser = new UserDto(username, email, password);
        userBean.updateUser(newUser);

        LOG.info("Updated user: " + username);
        request.getRequestDispatcher("/WEB-INF/userPages/profile.jsp").forward(request, response);
    }

}