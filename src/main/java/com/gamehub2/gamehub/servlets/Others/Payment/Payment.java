package com.gamehub2.gamehub.servlets.Others.Payment;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Others.CardDetailsDto;
import com.gamehub2.gamehub.ejb.Admins.AdminBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.ejb.Other.CardDetailsBean;
import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.PaymentRequestBean;
import com.gamehub2.gamehub.entities.Others.PaymentRequest;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "Payment", value = "/Payment")
public class Payment extends HttpServlet {

    @Inject
    CardDetailsBean cardDetailsBean;
    @Inject
    CartBean cartBean;
    @Inject
    PaymentRequestBean paymentRequestBean;
    @Inject
    AdminBean adminBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    private static final Logger LOG = Logger.getLogger(Payment.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered Payment.doGet " + request.getParameter("username") + " **\n");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        String page = request.getParameter("page");

        List<CardDetailsDto> allCard = cardDetailsBean.findAllCardDetails();
        // Retrieve card details of the user
        List<CardDetailsDto> cardDetails =  cardDetailsBean.findCardByUsername(user.getUsername(), allCard);
        request.setAttribute("cardDetails", cardDetails);

        LOG.info("\n** Exited Payment.doGet ... **\n");

        request.getRequestDispatcher("/WEB-INF/userPages/" + page + ".jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");
        String expirationDate = request.getParameter("expirationDate");
        double totalPrice = cartBean.getTotalPriceByCartId(cartBean.getCartIdByUsername(user.getUsername()));
        String cvv = request.getParameter("cvv");
        LOG.info("\n** " + cvv + cardName + cardNumber +  " **\n");

        String cardNumberRegex = "^\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$";

        String expirationDateRegex = "^(0[1-9]|1[0-2])/(\\d{2})$";


        Pattern cardNumberPattern = Pattern.compile(cardNumberRegex);
        Pattern expirationDatePattern = Pattern.compile(expirationDateRegex);


        Matcher cardNumberMatcher = cardNumberPattern.matcher(cardNumber);

        Matcher expirationDateMatcher = expirationDatePattern.matcher(expirationDate);


        boolean isValidCardNumber = cardNumberMatcher.matches();

        boolean isValidExpirationDate = expirationDateMatcher.matches();
        boolean isCardExpired = false;
        boolean saveCardDetails = "on".equals(request.getParameter("saveCardDetails"));
        if (isValidCardNumber && isValidExpirationDate) {

            LocalDate currentDate = LocalDate.now();
            LocalDate parsedExpirationDate;
            try {
                parsedExpirationDate = LocalDate.parse((expirationDate), DateTimeFormatter.ofPattern("MM/yy"));


                if (parsedExpirationDate.isBefore(currentDate)) {
                    isCardExpired = true;
                }
            } catch (DateTimeParseException e) {

                e.printStackTrace();
            }


            if (cvv.length() == 3 && !isCardExpired) {

                LOG.info("\n** Card is valid **\n");

                if (saveCardDetails) {
                    // Save the card details in the database
                    List<String> usernames = Collections.singletonList(user.getUsername());
                    cardDetailsBean.saveCardDetails(null, usernames, cardNumber, expirationDate, cardName);
                }
                List<Long> gamesIdInCart = cartBean.getGamesInCart(Long.parseLong(request.getParameter("cart")));
                LOG.info("\n** List created ... **\n");
                PaymentRequest pr = paymentRequestBean.createPaymentRequest(gamesIdInCart,user.getUsername(),cardNumber,expirationDate,totalPrice);
                LOG.info("\n** PR created ... **\n");
                AdminBean.newPaymentRequest(pr);
                LOG.info("\n** Added to admin list ... **\n");
                cartBean.clearCart(Long.parseLong(request.getParameter("cart")));
                request.getRequestDispatcher("/WEB-INF/userPages/thank-you.jsp").forward(request, response);
            } else {
                // If the card is not valid (CVV or expired card)
                LOG.info("\n** Card is not valid (CVV or expired card)  **\n");
                request.getRequestDispatcher("/Checkout?username=" + user.getUsername() + "&page=checkout").forward(request, response);
            }
        } else {
            // If the card is not valid (cardNumber or expiration date doesn't have correct format)
            LOG.info("\n** Card is not valid (cardNumber or expiration date doesn't have correct format)  **\n");
            request.getRequestDispatcher("/Checkout?username=" + user.getUsername() + "&page=checkout").forward(request, response);
        }
    }

}