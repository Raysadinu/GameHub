package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.common.Others.CardDetailsDto;
import com.gamehub2.gamehub.entities.Others.CardDetails;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CardDetailsBean {
    private static final Logger LOG = Logger.getLogger(WishlistBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    public List<CardDetailsDto> findAllCardDetails(){

        LOG.info("\n** Entered findAllCardDetails method. **\n");
        try {
            TypedQuery<CardDetails> typedQuery = entityManager.createQuery("SELECT cd FROM CardDetails cd", CardDetails.class);
            List<CardDetails> CardDetails = typedQuery.getResultList();
            LOG.info("\n** Exited findAllCardDetails method with the list size of: "+ CardDetails.size() +"**\n");
            return copyCardDetailsToDto(CardDetails);
        } catch (Exception ex) {
            LOG.info("\nError in findAllCardDetails method! "+ex.getMessage()+"\n");
            throw new EJBException(ex);
        }

    }

    private List<CardDetailsDto> copyCardDetailsToDto(List<CardDetails> cardDetailsList) {
        LOG.info("\n** Entered copyCardDetailsToDto method with list size: " + cardDetailsList.size() + "**\n");

        List<CardDetailsDto> listToReturn = new ArrayList<>();

        for (CardDetails cardDetails : cardDetailsList) {

            CardDetailsDto cardDetailsDtoTemp = new CardDetailsDto(
                    cardDetails.getCardId(),
                    cardDetails.getUser(),
                    cardDetails.getCardNumber(),
                    cardDetails.getExpirationDate(),
                    cardDetails.getCardName()
            );
            listToReturn.add(cardDetailsDtoTemp);
        }

        LOG.info("\n** Exited copyCardDetailsToDto method. **\n");
        return listToReturn;
    }

    public List<CardDetailsDto> findCardByUsername(String username, List<CardDetailsDto> allCards){
        LOG.info("\n** Entered findCardByUsername method with the username: "+ username +"**\n");
        List<CardDetailsDto> cards = new ArrayList<CardDetailsDto>();
        for (CardDetailsDto currentCardDetails : allCards) {
            for(User u : currentCardDetails.getUser())
            {
                if(u.getUsername().equals(username))
                {
                    LOG.info("\n** Exited findCardByUsernames method. **\n");
                    cards.add(currentCardDetails);
                }
            }
        }
        LOG.info("\n** Exited findCardByUsername method with NULL. **\n");
        return cards;
    }
    public void saveCardDetails(Long cardId, List<String> usernames, String cardNumber, String expirationDate, String cardName) {
        LOG.info("\n** Entered saveCardDetails method for cardId: " + cardId + " **\n");
        try {
            // Fetch the user entities based on the provided usernames
            TypedQuery<User> userQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.username IN :usernames", User.class);
            userQuery.setParameter("usernames", usernames);
            List<User> users = userQuery.getResultList();

            // Create a new CardDetails entity with the provided details
            CardDetails cardDetails = new CardDetails();
            cardDetails.setUser(users);
            cardDetails.setCardNumber(cardNumber);

            // Parse the expiration date string into a LocalDate object
            /*In this line, "01/" is added before the expiration date string to provide a complete date string in "dd/MM/yy" format.
            This allows the LocalDate.parse method to correctly interpret the date string as "01/expirationDate".*/
            LocalDate parsedExpirationDate = LocalDate.parse("01/" + expirationDate, DateTimeFormatter.ofPattern("dd/MM/yy"));


            // Format the expiration date to "MM/YY" format
            String formattedExpirationDate = DateTimeFormatter.ofPattern("MM/yy").format(parsedExpirationDate);

            // Set the expiration date and card name
            cardDetails.setExpirationDate(formattedExpirationDate);
            cardDetails.setCardName(cardName);

            // Persist the new CardDetails entity
            entityManager.persist(cardDetails);
            LOG.info("\n** Saved card details successfully for cardId: " + cardDetails.getCardId() + " **\n");
        } catch (Exception ex) {
            LOG.info("\nError in saveCardDetails method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }
}
