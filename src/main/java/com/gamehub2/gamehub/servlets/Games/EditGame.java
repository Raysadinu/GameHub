package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;

@DeclareRoles({"ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}))
@WebServlet(name = "EditGame", value = "/EditGame")
public class EditGame extends HttpServlet {
    @Inject
    GameDetailsBean gameDetailsBean;
    private static final Logger LOG = Logger.getLogger(EditGame.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long gameId = Long.valueOf(request.getParameter("gameId"));


        List<GameDetailsDto> gameList = gameDetailsBean.findAllGameDetails();
        GameDetailsDto selectedGame = gameDetailsBean.getGameDetailsByGameId(gameId, gameList);


        request.setAttribute("game", selectedGame);


        request.getRequestDispatcher("/WEB-INF/adminPages/editGame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String publisher = request.getParameter("publisher");
        String developer = request.getParameter("developer");
        String description = request.getParameter("description");
        String releaseDateString = request.getParameter("releaseDate");
        LocalDate releaseDate = null;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            releaseDate = LocalDate.parse(releaseDateString, formatter);
        } catch (DateTimeParseException e) {

            LOG.warning("Error parsing the release date: " + e.getMessage());
        }


        String gameIdString = request.getParameter("gameId");
        long gameId = Long.valueOf(gameIdString);


        GameDetailsDto newGameDetails = new GameDetailsDto(gameId, request.getParameter("gameName"), releaseDate, publisher, developer, description);


        gameDetailsBean.updateGameDetails(newGameDetails);


        LOG.info("Exited EditGame.doPost method.");


        response.sendRedirect(request.getContextPath() + "/Games");
    }
}