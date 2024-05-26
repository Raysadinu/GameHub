package com.gamehub2.gamehub.ejb.Games;

import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.entities.Games.PriceDetails;
import com.gamehub2.gamehub.servlets.Games.Games;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Stateless
public class PriceDetailsBean {
    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOG = Logger.getLogger(Games.class.getName());
    public List<PriceDetailsDto> findAllPriceDetails() {
        LOG.info("\n** Entered findAllPriceDetails method **\n");
        try {
            TypedQuery<PriceDetails> typedQuery = entityManager.createQuery("SELECT gd FROM PriceDetails gd", PriceDetails.class);
            List<PriceDetails> PriceDetailsList = typedQuery.getResultList();
            LOG.info("\n** Exited findAllPriceDetails method **\n");
            return copyPriceDetailsToDto(PriceDetailsList);
        } catch (Exception ex) {
            LOG.severe("\nError in findAllPriceDetails method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    private List<PriceDetailsDto> copyPriceDetailsToDto(List<PriceDetails> PriceDetailsList) {
        LOG.info("\n** Entered copyPriceDetailsToDto method with list size of: " + PriceDetailsList.size() + " **\n");

        List<PriceDetailsDto> listToReturn = new ArrayList<>();

        for (PriceDetails gd : PriceDetailsList) {
            PriceDetailsDto PriceDetailsDtoTemp = new PriceDetailsDto(gd.getGameId(), gd.getPrice(),gd.getDiscount(),gd.getDiscount_price());
            listToReturn.add(PriceDetailsDtoTemp);
        }

        LOG.info("\n** Exited copyPriceDetailsToDto method. **\n");
        return listToReturn;
    }

    public PriceDetailsDto getPriceDetailsByGameId(Long gameId, List<PriceDetailsDto> PriceDetailsDtoList) {
        LOG.info("\n** Entered getPriceDetailsByGameId method for the gameId: " + gameId + " and with a list size of: " + PriceDetailsDtoList.size() + " **\n");

        PriceDetailsDto gameToReturn = null;

        for (PriceDetailsDto gdd : PriceDetailsDtoList) {
            if (Objects.equals(gdd.getGameId(), gameId)) {
                gameToReturn = gdd;
            }
        }

        if (gameToReturn == null) {
            LOG.info("\n** No game returning NULL GAME **\n");
            gameToReturn = new PriceDetailsDto(Long.parseLong("-1"),0,0,0);
        }

        LOG.info("\n** Exited getPriceDetailsByGameId method. **\n");
        return gameToReturn;
    }


    public void updatePriceDetails(PriceDetailsDto newPriceDetails) {
        LOG.info("\n** Entered updatePriceDetails method for gameId: " + newPriceDetails.getGameId() + " **\n");

        PriceDetails PriceDetails = entityManager.find(PriceDetails.class, newPriceDetails.getGameId());
        if (PriceDetails == null) {
            throw new EJBException("PriceDetails with ID: " + newPriceDetails.getGameId() + " not found.");
        }
        PriceDetails.setPrice(newPriceDetails.getPrice());
        PriceDetails.setDiscount(newPriceDetails.getDiscount());
        PriceDetails.setDiscount_price(newPriceDetails.getDiscount_price());

        LOG.info("\n** Exited updatePriceDetails method. **\n");
    }

    public List<PriceDetails> findPricesByGameId(Long gameId) {
        LOG.info("\n** Entered findPricesByGameId method for gameId: " + gameId + " **\n");
        try {
            TypedQuery<PriceDetails> query = entityManager.createQuery("SELECT pd FROM PriceDetails pd WHERE pd.gameId = :gameId", PriceDetails.class);
            query.setParameter("gameId", gameId);
            List<PriceDetails> prices = query.getResultList();
            LOG.info("\n** Exited findPricesByGameId method. **\n");
            return prices;
        } catch (Exception ex) {
            LOG.severe("\nError in findPricesByGameId method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

}
