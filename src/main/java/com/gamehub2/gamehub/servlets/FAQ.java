package com.gamehub2.gamehub.servlets;

import java.io.IOException;

import jakarta.annotation.security.DeclareRoles;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;


@WebServlet(name = "FAQ", value = "/FAQ")
public class FAQ extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(FAQ.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/FAQ.jsp").forward(request,response);
    }


}