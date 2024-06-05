package com.gamehub2.gamehub.servlets.Admins;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Admins.AdminDto;
import com.gamehub2.gamehub.dto.Users.UserDto;
import com.gamehub2.gamehub.ejb.Admins.AdminBean;
import com.gamehub2.gamehub.ejb.Users.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "Admins", value = "/Admins")
public class Admins extends HttpServlet {

    @Inject
    AdminBean adminBean;
    @Inject
    UserBean userBean;
    private static final Logger LOG = Logger.getLogger(Admins.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AdminDto> admins = adminBean.findAllAdmins();
        List<UserDto> users =userBean.findAllUsers();
        request.setAttribute("admins", admins);
        request.setAttribute("users",users);
        request.getRequestDispatcher("/WEB-INF/adminPages/admins.jsp").forward(request, response);
    }

}