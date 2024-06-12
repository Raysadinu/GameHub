package com.gamehub2.gamehub.servlets.Others.Payment;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Admins.AdminDto;
import com.gamehub2.gamehub.dto.Games.GameDetailsDto;
import com.gamehub2.gamehub.dto.Others.PaymentRequestDto;
import com.gamehub2.gamehub.ejb.Admins.AdminBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Other.LibraryBean;
import com.gamehub2.gamehub.ejb.Other.NotificationBean;
import com.gamehub2.gamehub.ejb.Other.PaymentRequestBean;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.PaymentRequest;
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
    @Inject
    NotificationBean notificationBean;
    @Inject
    AdminBean adminBean;
    private static final Logger LOG = Logger.getLogger(PrAcceptOrDecline.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        String action = request.getParameter("action");
        Long paymentRequestId = Long.parseLong(request.getParameter("paymentrequestId"));

        LOG.info("\n** Pr.. [" + action + paymentRequestId + "] **\n");
        PaymentRequest.RequestStatus newStatus = Objects.equals(action, "A") ? PaymentRequest.RequestStatus.ACCEPTED : PaymentRequest.RequestStatus.REJECTED;
        paymentRequestBean.changeStatus(currentUser.getUsername(), paymentRequestId, newStatus);

        if (newStatus == PaymentRequest.RequestStatus.ACCEPTED) {
            List<Game> gamesInPr = paymentRequestBean.findGamesInPaymentReq(paymentRequestId);
            String paymentRequestUsername = paymentRequestBean.findUsernameByPaymentReqId(paymentRequestId);
            for (Game g : gamesInPr) {
                Long libraryId = libraryBean.getLibraryIdByUsername(paymentRequestUsername);
                libraryBean.addGameToLibrary(libraryId, g.getGameId());
            }
        }
        String notificationMessage;
        if (newStatus == PaymentRequest.RequestStatus.ACCEPTED) {
            notificationMessage = "Payment has been accepted. The game has been added to your library.";
        } else {
            notificationMessage = "Payment has been rejected. Please check your payment information and try again.";
        }
        notificationBean.sendPaymentNotification(currentUser.getUsername(), notificationMessage);
        List<AdminDto> administrators = adminBean.findAllAdmins();
        for (AdminDto admin : administrators) {
            notificationBean.sendPaymentNotification(admin.getUsername(), "A payment request has been processed. Check the payment-requests page for details.");
        }
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

        request.getRequestDispatcher("/WEB-INF/adminPages/payment-requests.jsp").forward(request, response);
    }


}