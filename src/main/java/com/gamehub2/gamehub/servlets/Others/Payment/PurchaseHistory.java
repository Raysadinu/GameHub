package com.gamehub2.gamehub.servlets.Others.Payment;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Games.GameDetailsDto;
import com.gamehub2.gamehub.dto.Others.PaymentRequestDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Other.PaymentRequestBean;
import com.gamehub2.gamehub.entities.Games.Game;
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
import java.util.logging.Logger;


@WebServlet(name = "PurchaseHistory", value = "/PurchaseHistory")
public class PurchaseHistory extends HttpServlet {


    @Inject
    PaymentRequestBean paymentRequestBean;
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    GameBean gameBean;
    private static final Logger LOG = Logger.getLogger(PurchaseHistory.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<PaymentRequestDto> allPaymentRequests = paymentRequestBean.findAllPaymentRequests();
        List<PaymentRequestDto> userPaymentRequests = paymentRequestBean.findPaymentRequestsByUsername(user.getUsername(), allPaymentRequests);
        List<Game> gamesInPaymentRequest = new ArrayList<>();
        for (PaymentRequestDto paymentRequest : userPaymentRequests) {
            gamesInPaymentRequest.addAll(paymentRequest.getGames());
        }
        List<GameDetailsDto> gameDetailsList = gameDetailsBean.findAllGameDetails();

        List<Long> gameIdList = new ArrayList<>();
        for(Game game: gamesInPaymentRequest){
            gameIdList.add(game.getGameId());
        }

        List<GameDetailsDto> gameDetailsForGamesInPaymentRequest = new ArrayList<>();
        for(GameDetailsDto game: gameDetailsList){
            if(gameIdList.contains(game.getGameId())){
                gameDetailsForGamesInPaymentRequest.add(game);
            }
        }
        request.setAttribute("games",gameDetailsForGamesInPaymentRequest);
        request.setAttribute("paymentRequests", userPaymentRequests);
        request.getRequestDispatcher("/WEB-INF/userPages/purchase-history.jsp").forward(request,response);
    }

}