
package com.gamehub2.gamehub.servlets;

import java.io.IOException;

import com.gamehub2.gamehub.Utilities.Functionalities;
import com.gamehub2.gamehub.common.Games.CategoryDto;
import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.common.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Games.*;
import com.gamehub2.gamehub.ejb.Other.WishlistBean;
import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.LibraryBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import com.gamehub2.gamehub.entities.Games.GamePG;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.*;
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
    @Inject
    CategoryBean categoryBean;
    @Inject
    UserDetailsBean userDetailsBean;
    @Inject
    GamePGBean gamePGBean;
    private static final Logger LOG = Logger.getLogger(Home.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("\n** Home .doGet **\n");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        UserDetailsDto userDetailsDto = userDetailsBean.getUserDetailsByUsername(user.getUsername(), userDetailsBean.findAllUserDetails());
        int userAge;
        if(userDetailsDto.getBirthDate()!=null){
            userAge = Functionalities.calculateAge(userDetailsDto.getBirthDate());
        }else
        {
            userAge = -1;
        }

        List<GamePG.PGType> validPG = Functionalities.getValidPG(userAge);


        List<GameDetailsDto> gameDetails = new ArrayList<>();
        List<GameDetailsDto> gameDetailsOnWishlist = new ArrayList<>();
        List<GameDetailsDto> gameDetailsInCart = new ArrayList<>();
        List<GameDetailsDto> gameDetailsInLibrary = new ArrayList<>();


        List<GameDetailsDto> games = gameDetailsBean.findAllGameDetails();

        List<PriceDetailsDto> priceList = priceDetailsBean.findAllPriceDetails();
        List<CategoryDto> categories = categoryBean.findAllCategories();
        categories.sort((c1, c2) -> c1.getCategoryName().compareToIgnoreCase(c2.getCategoryName()));

        Map<Long, Double[]> gamePrices = Functionalities.calculateGamePrices(games, priceList);

        for (GameDetailsDto game : games) {
            boolean inWishlist = wishlistBean.inWishlist(user.getUsername(), game.getGameId());
            boolean inLibrary = libraryBean.inLibrary(user.getUsername(), game.getGameId());
            boolean inCart = cartBean.inCart(user.getUsername(), game.getGameId());
            GamePG.PGType thisGamePG = gamePGBean.getPGTypeByGameId(game.getGameId());

            if(validPG.contains(thisGamePG)){
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
        }
        request.setAttribute("userAge", userAge);
        request.setAttribute("categories", categories);
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
