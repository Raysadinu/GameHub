package com.gamehub2.gamehub.servlets;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.GameDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@DeclareRoles({"USER","ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
@WebServlet(name = "Home", value = "/Home")

public class Home extends HttpServlet {
    @Inject
    GameDetailsBean gameDetailsBean;
    /* @Inject
     WishlistBean wishlistBean;
     @Inject
     CartBean cartBean;*/
    @Inject
    GameBean gameBean;
    private static final Logger LOG = Logger.getLogger(Home.class.getName());

    //todo check daca jocul este si/sau in cos si wishlist SAU 4 liste
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Home .doGet **\n");

        //List<WishlistDto> allWishlists = wishlistBean.findAllWishlists();

        //List<CartDto> allCarts = cartBean.findAllCarts();

        //WishlistDto wishlist = wishlistBean.findWishlistByUsername("Raysa", allWishlists);
        //CartDto cart = cartBean.findCartByUsername("Raysa", allCarts);


        //List<GameDto> gamesOnWishlist = gameBean.copyGamesToDto(wishlist.getGames());


        List<GameDetailsDto> gameDetailsOnWishlist = new ArrayList<>();


        List<GameDetailsDto> gameDetailsNotOnWishlist = new ArrayList<>();

        List<GameDetailsDto> games = gameDetailsBean.findAllGameDetails();

        List<Long> gameIdList = new ArrayList<>();

        //Wishlist
        /*for (GameDto game : gamesOnWishlist) {
            gameIdList.add(game.getGameId());
        }*/
        for (GameDetailsDto game : games) {
            if (gameIdList.contains(game.getGameId())) {
                gameDetailsOnWishlist.add(game);
            } else {
                gameDetailsNotOnWishlist.add(game);
            }
        }


        request.setAttribute("games", gameDetailsNotOnWishlist);
        request.setAttribute("wishlist", gameDetailsOnWishlist);


        LOG.info("\n** Exit Home .doGet **\n");
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);

    }
}