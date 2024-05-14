package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.common.Others.PaymentRequestDto;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Admins.Admin;
import com.gamehub2.gamehub.entities.Others.CardDetails;
import com.gamehub2.gamehub.entities.Others.PaymentRequest;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PaymentRequestBean {
    private static final Logger LOG = Logger.getLogger(PaymentRequestBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public void changeStatus(String adminUsername, Long paymentrequestId, PaymentRequest.RequestStatus newStatus) {
        PaymentRequest pr = entityManager.find(PaymentRequest.class,paymentrequestId);
        pr.setStatus(newStatus);
        pr.getAdmins().add(entityManager.find(Admin.class,adminUsername));
    }

    public List<PaymentRequestDto> findAllPaymentRequests() {
        LOG.info("** Entered findAllPaymentRequests method. **");
        try {
            TypedQuery<PaymentRequest> query = entityManager.createQuery("SELECT pr FROM PaymentRequest pr", PaymentRequest.class);
            List<PaymentRequest> paymentRequests = query.getResultList();
            LOG.info("** Exited findAllPaymentRequests method with the list size of: " + paymentRequests.size() + "**");
            return copyPaymentRequestsToDto(paymentRequests);
        } catch (Exception ex) {
            LOG.severe("Error in findAllPaymentRequests method: " + ex.getMessage());
            throw new EJBException(ex);
        }
    }

    private List<PaymentRequestDto> copyPaymentRequestsToDto(List<PaymentRequest> paymentRequests) {
        LOG.info("** Entered copyPaymentRequestsToDto method with list size: " + paymentRequests.size() + "**");

        List<PaymentRequestDto> dtos = new ArrayList<>();

        for (PaymentRequest paymentRequest : paymentRequests) {
            PaymentRequestDto dto = new PaymentRequestDto(
                    paymentRequest.getPaymentReqId(),
                    paymentRequest.getAdmins(),
                    paymentRequest.getGames(),
                    paymentRequest.getCard(),
                    paymentRequest.getUser(),
                    paymentRequest.getStatus().toString()
            );
            dtos.add(dto);
        }

        LOG.info("** Exited copyPaymentRequestsToDto method. **");
        return dtos;
    }
    public List<PaymentRequestDto> findPaymentRequestsByUsername(String username, List<PaymentRequestDto> allPaymentRequests) {
        LOG.info("** Entered findPaymentRequestsByUsername method with username: " + username + " **");
        List<PaymentRequestDto> paymentRequests = new ArrayList<>();

        for (PaymentRequestDto paymentRequest : allPaymentRequests) {
            User user = paymentRequest.getUser();
            if (user != null && user.getUsername().equals(username)) {
                LOG.info("** Found payment request for username: " + username + " **");
                paymentRequests.add(paymentRequest);
            }
        }

        LOG.info("** Exited findPaymentRequestsByUsername method with the list size of: " + paymentRequests.size() + " **");
        return paymentRequests;
    }
    public  PaymentRequest createPaymentRequest(List<Long> gamesIdInCart, String username, String cardNumber, String expirationDate) {
        PaymentRequest pr = new PaymentRequest();
        pr.setUser(entityManager.find(User.class,username));
        pr.setStatus(PaymentRequest.RequestStatus.PENDING);
        pr.setAdmins(new ArrayList<Admin>());
        CardDetails c = new CardDetails();
        c.setCardNumber(cardNumber);
        c.setExpirationDate(expirationDate);
        pr.setCard(c);
        List<Game> games = new ArrayList<Game>();
        for(Long Id : gamesIdInCart){
            games.add(entityManager.find(Game.class,Id));
        }
        pr.setGames(games);
        entityManager.persist(pr);
        return pr;
    }

    public List<Game> findGamesInPaymentReq(Long paymentrequestId) {
        PaymentRequest pr = entityManager.find(PaymentRequest.class,paymentrequestId);
        return pr.getGames();
    }

    public String findUsernameByPaymentReqId(Long paymentrequestId) {
        return entityManager.find(PaymentRequest.class,paymentrequestId).getUser().getUsername();
    }
}