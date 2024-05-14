package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.ejb.Admins.AdminBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "GameProfile", value = "/GameProfile")
public class GameProfile extends HttpServlet {
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    AdminBean adminBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    private static final Logger LOG = Logger.getLogger(GameProfile.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long gameId = Long.valueOf(request.getParameter("gameId"));

        LOG.info("Request received for gameId: " + gameId);
        LOG.info("Entering doGet method of Users servlet");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean isAdmin = adminBean.isAdmin(user.getUsername());
        request.setAttribute("isAdmin", isAdmin);



        List<GameDetailsDto> allGameDetails = gameDetailsBean.findAllGameDetails();
        GameDetailsDto thisGame = gameDetailsBean.getGameDetailsByGameId(gameId, allGameDetails);

        List<PriceDetailsDto> allPriceDetails = priceDetailsBean.findAllPriceDetails();
        PriceDetailsDto price = priceDetailsBean.getPriceDetailsByGameId(gameId, allPriceDetails);
        if (thisGame != null) {
            LOG.info("Game details retrieved: " + thisGame.toString());
            request.setAttribute("price", price);
            request.setAttribute("game", thisGame);
            request.getRequestDispatcher("/WEB-INF/gamePages/gameProfile.jsp").forward(request, response);
        } else {

            LOG.warning("Game details not found for gameId: " + gameId);

            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Game details not found");
        }
    }
}