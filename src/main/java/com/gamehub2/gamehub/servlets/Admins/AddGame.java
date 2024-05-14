package com.gamehub2.gamehub.servlets.Admins;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Games.GameBean;
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


@WebServlet(name = "AddGame", value = "/AddGame")
public class AddGame extends HttpServlet {
    @Inject
    GameBean gameBean;
    private static final Logger LOG = Logger.getLogger(AddGame.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("GET request received for AddGame servlet");

        request.getRequestDispatcher("/WEB-INF/adminPages/addGame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the game name parameter from the request
        String gameName = request.getParameter("game_name");

        LOG.info("Received game name: " + gameName);

        gameBean.addGame(gameName);

        LOG.info("Game added successfully: " + gameName);

        response.sendRedirect(request.getContextPath() + "/Games");
    }
}