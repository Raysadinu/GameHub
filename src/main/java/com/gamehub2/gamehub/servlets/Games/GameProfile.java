package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;

import com.gamehub2.gamehub.Utilities.Functionalities;
import com.gamehub2.gamehub.common.Games.CategoryDto;
import com.gamehub2.gamehub.common.Games.CommentDto;
import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.ejb.Admins.AdminBean;
import com.gamehub2.gamehub.ejb.Games.CategoryBean;
import com.gamehub2.gamehub.ejb.Games.CommentBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.LibraryBean;
import com.gamehub2.gamehub.ejb.Other.WishlistBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@WebServlet(name = "GameProfile", value = "/GameProfile")
public class GameProfile extends HttpServlet {
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    AdminBean adminBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    @Inject
    CategoryBean categoryBean;
    @Inject
    CommentBean commentBean;
    @Inject
    WishlistBean wishlistBean;
    @Inject
    CartBean cartBean;
    @Inject
    LibraryBean libraryBean;

    private static final Logger LOG = Logger.getLogger(GameProfile.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long gameId = Long.valueOf(request.getParameter("gameId"));

        LOG.info("Request received for gameId: " + gameId);
        LOG.info("Entering doGet method of GameProfile servlet");

        // Retrieving the user from the session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Checking if the user is an admin
        boolean isAdmin = adminBean.isAdmin(user.getUsername());
        request.setAttribute("isAdmin", isAdmin);


        List<GameDetailsDto> allGameDetails = gameDetailsBean.findAllGameDetails();

        GameDetailsDto thisGame = gameDetailsBean.getGameDetailsByGameId(gameId, allGameDetails);

        List<PriceDetailsDto> allPriceDetails = priceDetailsBean.findAllPriceDetails();

        Map<Long, Double[]> gamePrices = Functionalities.calculateGamePrices(allGameDetails, allPriceDetails);

        List<CategoryDto> categories = categoryBean.getCategoriesByGameId(gameId);
        categories.sort((c1, c2) -> c1.getCategoryName().compareToIgnoreCase(c2.getCategoryName()));
        List<CommentDto> comments = commentBean.getCommentsByGameId(gameId);

        List<GameDetailsDto> gameDetails = new ArrayList<>();
        List<GameDetailsDto> gameDetailsOnWishlist = new ArrayList<>();
        List<GameDetailsDto> gameDetailsInCart = new ArrayList<>();
        List<GameDetailsDto> gameDetailsInLibrary = new ArrayList<>();

        for (GameDetailsDto game : allGameDetails) {
            boolean inWishlist = wishlistBean.inWishlist(user.getUsername(), game.getGameId());
            boolean inLibrary = libraryBean.inLibrary(user.getUsername(), game.getGameId());
            boolean inCart = cartBean.inCart(user.getUsername(), game.getGameId());
            gameDetails.add(game);
            if (inWishlist) {
                gameDetailsOnWishlist.add(game);
            }
            if (inCart) {
                gameDetailsInCart.add(game);
            }
            if (inLibrary) {
                gameDetailsInLibrary.add(game);
            }
        }

        if (thisGame != null) {
            LOG.info("Game details retrieved: " + thisGame.toString());


            request.setAttribute("price", gamePrices);
            request.setAttribute("game", thisGame);
            request.setAttribute("categories", categories);
            request.setAttribute("comments", comments);
            request.setAttribute("wishlist", gameDetailsOnWishlist);
            request.setAttribute("cart", gameDetailsInCart);
            request.setAttribute("library", gameDetailsInLibrary);

            request.getRequestDispatcher("/WEB-INF/gamePages/gameProfile.jsp").forward(request, response);
        } else {

            LOG.warning("Game details not found for gameId: " + gameId);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Game details not found");
        }
    }
}