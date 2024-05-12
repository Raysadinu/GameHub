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

import java.util.List;
import java.util.logging.Logger;

@DeclareRoles({"USER", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER", "ADMIN"}))
@WebServlet(name = "GameProfile", value = "/GameProfile")
public class GameProfile extends HttpServlet {
    @Inject
    GameDetailsBean gameDetailsBean;
    private static final Logger LOG = Logger.getLogger(GameProfile.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long gameId = Long.valueOf(request.getParameter("gameId"));

        LOG.info("Request received for gameId: " + gameId);

        List<GameDetailsDto> allGameDetails = gameDetailsBean.findAllGameDetails();
        GameDetailsDto thisGame = gameDetailsBean.getGameDetailsByGameId(gameId, allGameDetails);

        if (thisGame != null) {
            LOG.info("Game details retrieved: " + thisGame.toString());

            request.setAttribute("game", thisGame);
            request.getRequestDispatcher("/WEB-INF/gamePages/gameProfile.jsp").forward(request, response);
        } else {

            LOG.warning("Game details not found for gameId: " + gameId);

            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Game details not found");
        }
    }
}