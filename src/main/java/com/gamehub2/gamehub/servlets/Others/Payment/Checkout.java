package com.gamehub2.gamehub.servlets.Others.Payment;

import java.io.IOException;

import com.gamehub2.gamehub.Utilities.Functionalities;
import com.gamehub2.gamehub.dto.Games.GameDetailsDto;
import com.gamehub2.gamehub.dto.Games.GameDto;
import com.gamehub2.gamehub.dto.Games.PriceDetailsDto;
import com.gamehub2.gamehub.dto.Others.CardDetailsDto;
import com.gamehub2.gamehub.dto.Others.CartDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.ejb.Other.CardDetailsBean;
import com.gamehub2.gamehub.ejb.Other.CartBean;
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


@WebServlet(name = "Checkout", value = "/Checkout")
public class Checkout extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Checkout.class.getName());
    @Inject
    CardDetailsBean cardDetailsBean;
    @Inject
    GameBean gameBean;
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    CartBean cartBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered Payment.doGet " + request.getParameter("username") + " **\n");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        String page = request.getParameter("page");

        List<CardDetailsDto> allCard = cardDetailsBean.findAllCardDetails();
        List<CardDetailsDto> cardDetails =  cardDetailsBean.findCardByUsername(user.getUsername(), allCard);
        request.setAttribute("cardDetails", cardDetails);

        LOG.info("\n** Exited Payment.doGet ... **\n");

        request.getRequestDispatcher("/WEB-INF/userPages/" + page + ".jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String page = request.getParameter("page");

        List<CardDetailsDto> allCard = cardDetailsBean.findAllCardDetails();
        List<CardDetailsDto> cardDetails =  cardDetailsBean.findCardByUsername(user.getUsername(), allCard);



        request.setAttribute("cardDetails", cardDetails);


        List<CartDto> allCarts = cartBean.findAllCarts();
        CartDto cart = cartBean.findCartByUsername(user.getUsername(), allCarts);
        double totalPrice = cartBean.getTotalPriceByCartId(cart.getCartId());
        LOG.info("Total Price: " + totalPrice);

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
        List<PriceDetailsDto> priceList = priceDetailsBean.findAllPriceDetails();


        Map<Long, Double[]> gamePrices = Functionalities.calculateGamePrices(gameDetailsList, priceList);

        request.setAttribute("user",user);
        request.setAttribute("cart",cart);
        request.setAttribute("games",gameDetailsForGamesInCart);
        request.setAttribute("gamePrices", gamePrices);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/WEB-INF/userPages/" + page + ".jsp").forward(request, response);
    }
}