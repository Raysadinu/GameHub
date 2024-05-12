package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.GameDto;
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

import java.util.List;
import java.util.logging.Logger;

@DeclareRoles({"ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}))
@WebServlet(name = "Games", value = "/Games")
public class Games extends HttpServlet {
    @Inject
    GameBean gameBean;
    private static final Logger LOG = Logger.getLogger(Games.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log entry
        LOG.info("Entering doGet method of Games servlet");

        try {
            List<GameDto> games = gameBean.findAllGames();
            request.setAttribute("games", games);
            request.getRequestDispatcher("/WEB-INF/adminPages/games.jsp").forward(request, response);
        } catch (Exception e) {
            // Log any exceptions that occur
            LOG.severe("An error occurred while processing doGet: " + e.getMessage());
            // Optionally, you can rethrow the exception or handle it differently based on your application's requirements
            throw new ServletException("Error processing doGet", e);
        } finally {
            // Log exit
            LOG.info("Exiting doGet method of Games servlet");
        }
    }
}