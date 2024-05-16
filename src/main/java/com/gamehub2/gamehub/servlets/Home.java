package com.gamehub2.gamehub.servlets;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.GameDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.common.Others.WishlistDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.ejb.Other.WishlistBean;
import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.LibraryBean;
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



@WebServlet(name = "Home", value = "/Home")

public class Home extends HttpServlet {
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    @Inject
    WishlistBean wishlistBean;
    @Inject
    CartBean cartBean;
    @Inject
    LibraryBean libraryBean;
    private static final Logger LOG = Logger.getLogger(Home.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("\n** Home .doGet **\n");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<GameDetailsDto> gameDetails = new ArrayList<>();


        List<GameDetailsDto> gameDetailsOnWishlist = new ArrayList<>();
        List<GameDetailsDto> gameDetailsInCart = new ArrayList<>();
        List<GameDetailsDto> gameDetailsInLibrary = new ArrayList<>();


        List<GameDetailsDto> games = gameDetailsBean.findAllGameDetails();

        List<PriceDetailsDto> priceList = priceDetailsBean.findAllPriceDetails();

        Map<Long, Double[]> gamePrices = new HashMap<>();

        for (GameDetailsDto gameDetail : games) {
            Long gameId = gameDetail.getGameId();
            Double[] priceInfo = new Double[3];

            for (PriceDetailsDto priceDetail : priceList) {
                if (priceDetail.getGameId().equals(gameId)) {
                    if (priceDetail.getDiscount() > 0) {
                        priceInfo[0] = priceDetail.getPrice();
                        priceInfo[1] = priceDetail.getDiscount_price();
                        priceInfo[2] = priceDetail.getDiscount();
                    } else {
                        priceInfo[0] = priceDetail.getPrice();
                        priceInfo[1] = 0.0;
                        priceInfo[2] = 0.0;
                    }
                    break;
                }
            }
            gamePrices.put(gameId, priceInfo);
        }


        for (GameDetailsDto game : games) {
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

        request.setAttribute("user", user);
        request.setAttribute("gamePrices", gamePrices);
        request.setAttribute("games", gameDetails);
        request.setAttribute("wishlist", gameDetailsOnWishlist);
        request.setAttribute("cart", gameDetailsInCart);
        request.setAttribute("library", gameDetailsInLibrary);
        LOG.info("\n** Exit Home .doGet **\n");
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}