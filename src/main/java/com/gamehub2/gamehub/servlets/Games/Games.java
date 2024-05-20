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

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "Games", value = "/Games")
public class Games extends HttpServlet {
    @Inject
    GameBean gameBean;
    private static final Logger LOG = Logger.getLogger(Games.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("Entering doGet method of Games servlet");

        try {
            List<GameDto> games = gameBean.findAllGames();
            games.sort(Comparator.comparingLong(GameDto::getGameId));
            request.setAttribute("games", games);
            request.getRequestDispatcher("/WEB-INF/adminPages/games.jsp").forward(request, response);
        } catch (Exception e) {

            LOG.severe("An error occurred while processing doGet: " + e.getMessage());

            throw new ServletException("Error processing doGet", e);

        } finally {

            LOG.info("Exiting doGet method of Games servlet");
        }
    }
}