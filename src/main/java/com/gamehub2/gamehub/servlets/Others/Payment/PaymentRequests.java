package com.gamehub2.gamehub.servlets.Others.Payment;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Games.GameDetailsDto;
import com.gamehub2.gamehub.dto.Others.PaymentRequestDto;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Other.PaymentRequestBean;
import com.gamehub2.gamehub.entities.Games.Game;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "PaymentRequests", value = "/PaymentRequests")
public class PaymentRequests extends HttpServlet {

    @Inject
    PaymentRequestBean paymentRequestBean;
    @Inject
    GameDetailsBean gameDetailsBean;

    private static final Logger LOG = Logger.getLogger(PaymentRequests.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<PaymentRequestDto> allPaymentRequests = paymentRequestBean.findAllPaymentRequests();
        List<Game> gamesInPaymentRequest = new ArrayList<>();
        for (PaymentRequestDto paymentRequest : allPaymentRequests) {
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
        request.setAttribute("paymentRequests", allPaymentRequests);
        request.getRequestDispatcher("/WEB-INF/adminPages/payment-requests.jsp").forward(request,response);
    }

}