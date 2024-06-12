package com.gamehub2.gamehub.servlets.Others.Cart;

import java.io.IOException;

import com.gamehub2.gamehub.Utilities.Functionalities;
import com.gamehub2.gamehub.dto.Games.GameDetailsDto;
import com.gamehub2.gamehub.dto.Games.GameDto;
import com.gamehub2.gamehub.dto.Games.PriceDetailsDto;
import com.gamehub2.gamehub.dto.Others.CartDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.PaymentRequestBean;
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
    @Inject
    PaymentRequestBean paymentRequestBean;
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


        Map<Long, Double[]> gamePrices = Functionalities.gamePrices(gameDetailsList, priceList);

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