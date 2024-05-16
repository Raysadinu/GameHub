package com.gamehub2.gamehub.servlets.Others.Cart;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.GameDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.common.Others.CartDto;
import com.gamehub2.gamehub.common.Others.WishlistDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.WishlistBean;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@WebServlet(name = "Cart", value = "/Cart")
public class Cart extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Cart.class.getName());

    @Inject
    GameBean gameBean;
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    CartBean cartBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    @Inject
    WishlistBean wishlistBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered Wishlist.doGet ... **\n");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        List<CartDto> allCarts =cartBean.findAllCarts();
        CartDto cart = cartBean.findCartByUsername(user.getUsername(), allCarts);
        double totalPrice = cartBean.getTotalPriceByCartId(cart.getCartId());

        LOG.info("Total Price: " + totalPrice);

        List<GameDetailsDto> gameDetailsOnWishlist = new ArrayList<>();
        List<GameDto> gamesInCart = gameBean.copyGamesToDto(cart.getGames());
        List<GameDetailsDto> gameDetailsList = gameDetailsBean.findAllGameDetails();

        List<Long> gameIdList = new ArrayList<>();
        for(GameDto game: gamesInCart){
            gameIdList.add(game.getGameId());
        }

        List<GameDetailsDto> gameDetailsForGamesInCart = new ArrayList<>();
        for(GameDetailsDto game: gameDetailsList){
            if(gameIdList.contains(game.getGameId())){
                gameDetailsForGamesInCart.add(game);
            }
        }
        for (GameDetailsDto game : gameDetailsList) {
            boolean inWishlist = wishlistBean.inWishlist(user.getUsername(), game.getGameId());
            if (inWishlist) {
                gameDetailsOnWishlist.add(game);
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

        request.setAttribute("user", user);
        request.setAttribute("cart",cart);
        request.setAttribute("wishlist", gameDetailsOnWishlist);
        request.setAttribute("games",gameDetailsForGamesInCart);
        request.setAttribute("gamePrices", gamePrices);
        request.setAttribute("totalPrice", totalPrice);

        LOG.info("\n** Exited Cart.doGet ... **\n");
        request.getRequestDispatcher("/WEB-INF/userPages/cart.jsp").forward(request,response);
    }
}