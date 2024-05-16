package com.gamehub2.gamehub.servlets.Others.Wishlist;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.GameDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.common.Others.WishlistDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.WishlistBean;
import com.gamehub2.gamehub.ejb.Users.UserBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(name = "Wishlist", value = "/Wishlist")
public class Wishlist extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Wishlist.class.getName());

    @Inject
    GameBean gameBean;
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    WishlistBean wishlistBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    @Inject
    CartBean cartBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered Wishlist.doGet ... **\n");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        List<WishlistDto> allWishlists = wishlistBean.findAllWishlists();
        WishlistDto wishlist = wishlistBean.findWishlistByUsername(user.getUsername(), allWishlists);
        double totalPrice = wishlistBean.getTotalPriceByWishlistId(wishlist.getWishlistId());
        LOG.info("Total Price: " + totalPrice);

        List<GameDetailsDto> gameDetails = new ArrayList<>();
        List<GameDetailsDto> gameDetailsInCart = new ArrayList<>();

        List<GameDto> gamesOnWishlist = gameBean.copyGamesToDto(wishlist.getGames());
        List<GameDetailsDto> gameDetailsList = gameDetailsBean.findAllGameDetails();

        List<Long> gameIdList = new ArrayList<>();
        for (GameDto game : gamesOnWishlist) {
            gameIdList.add(game.getGameId());
        }

        List<GameDetailsDto> gameDetailsForGamesOnWishlist = new ArrayList<>();
        for (GameDetailsDto game : gameDetailsList) {
            if (gameIdList.contains(game.getGameId())) {
                gameDetailsForGamesOnWishlist.add(game);
            }
        }
        List<PriceDetailsDto> priceList = priceDetailsBean.findAllPriceDetails();


        Map<Long, Double[]> gamePrices = new HashMap<>();

        for (GameDetailsDto gameDetail : gameDetailsList) {
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


        request.setAttribute("cart", gameDetailsInCart);
        request.setAttribute("user", user);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("wishlist", wishlist);
        request.setAttribute("games", gameDetailsForGamesOnWishlist);
        request.setAttribute("gamePrices", gamePrices);

        LOG.info("\n** Exited Wishlist.doGet ... **\n");
        request.getRequestDispatcher("/WEB-INF/userPages/wishlist.jsp").forward(request, response);
    }
}
