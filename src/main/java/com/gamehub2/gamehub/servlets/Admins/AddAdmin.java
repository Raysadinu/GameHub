package com.gamehub2.gamehub.servlets.Admins;

import java.io.IOException;

import com.gamehub2.gamehub.common.Users.UserDto;
import com.gamehub2.gamehub.ejb.Admins.AdminBean;
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


@WebServlet(name = "AddAdmin", value = "/AddAdmin")
public class AddAdmin extends HttpServlet {
    @Inject
    UserBean userBean;

    @Inject
    AdminBean adminBean;

    private static final Logger LOG = Logger.getLogger(AddAdmin.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/adminPages/addAdmin.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");


        List<UserDto> allUsers = userBean.findAllUsers();

        UserDto user = userBean.findUserByUsername(username, allUsers);
        if (user != null) {

            if (!adminBean.isAdmin(username)) {

                adminBean.addAdmin(username);
                response.sendRedirect(request.getContextPath() + "/Admins");
                return;
            } else {

                request.setAttribute("alertMessage", "User is already an admin.");
                request.getRequestDispatcher("/WEB-INF/adminPages/addAdmin.jsp").forward(request, response);
                return;
            }
        } else {

            request.setAttribute("alertMessage", "User does not exist.");
            request.getRequestDispatcher("/WEB-INF/adminPages/addAdmin.jsp").forward(request, response);
            return;
        }
    }
}