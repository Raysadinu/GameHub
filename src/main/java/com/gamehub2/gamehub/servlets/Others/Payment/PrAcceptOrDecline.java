package com.gamehub2.gamehub.servlets.Others.Payment;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Others.PaymentRequestDto;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Other.LibraryBean;
import com.gamehub2.gamehub.ejb.Other.PaymentRequestBean;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.PaymentRequest;
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
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


@WebServlet(name = "PrAcceptOrDecline", value = "/PrAcceptOrDecline")
public class PrAcceptOrDecline extends HttpServlet {

    @Inject
    PaymentRequestBean paymentRequestBean;
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    LibraryBean libraryBean;
    private static final Logger LOG = Logger.getLogger(PrAcceptOrDecline.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        Long paymentRequestId = Long.parseLong(request.getParameter("paymentrequestId"));

        LOG.info("\n** Pr.. [" + action + paymentRequestId + "] **\n");
        paymentRequestBean.changeStatus(currentUser.getUsername(), paymentRequestId, Objects.equals(action, "A") ? PaymentRequest.RequestStatus.ACCEPTED : PaymentRequest.RequestStatus.REJECTED);

        List<PaymentRequestDto> allPaymentRequests = paymentRequestBean.findAllPaymentRequests();
        List<Game> gamesInPaymentRequest = new ArrayList<>();
        for (PaymentRequestDto paymentRequest : allPaymentRequests) {
            gamesInPaymentRequest.addAll(paymentRequest.getGames());
        }
        List<GameDetailsDto> gameDetailsList = gameDetailsBean.findAllGameDetails();

        List<Long> gameIdList = new ArrayList<>();
        for (Game game : gamesInPaymentRequest) {
            gameIdList.add(game.getGameId());
        }

        List<GameDetailsDto> gameDetailsForGamesInPaymentRequest = new ArrayList<>();
        for (GameDetailsDto game : gameDetailsList) {
            if (gameIdList.contains(game.getGameId())) {
                gameDetailsForGamesInPaymentRequest.add(game);
            }
        }

        request.setAttribute("games", gameDetailsForGamesInPaymentRequest);
        request.setAttribute("paymentRequests", allPaymentRequests);

        List<Game> gamesInPr = paymentRequestBean.findGamesInPaymentReq(paymentRequestId);
        String paymentRequestUsername = paymentRequestBean.findUsernameByPaymentReqId(paymentRequestId);
        for (Game g : gamesInPr) {
            Long libraryId = libraryBean.getLibraryIdByUsername(paymentRequestUsername);
            libraryBean.addGameToLibrary(libraryId, g.getGameId());

        }

        request.getRequestDispatcher("/WEB-INF/adminPages/payment-requests.jsp").forward(request, response);
    }


}