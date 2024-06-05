package com.gamehub2.gamehub.ejb.SystemReq;

import com.gamehub2.gamehub.dto.SystemReq.VideoCardDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.entities.SystemReq.VideoCard;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class VideoCardBean {
    private static final Logger LOG = Logger.getLogger(GameBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    public List<VideoCardDto> findAllVideoCards() {
        LOG.info("\n** Entering findAllVideoCards method **\n");
        try {
            TypedQuery<VideoCard> typedQuery = entityManager.createQuery("SELECT v FROM VideoCard v", VideoCard.class);
            List<VideoCard> videoCards = typedQuery.getResultList();
            LOG.info("\n** Exited findAllVideoCards method **\n");
            return copyVideoCardsToDto(videoCards);
        } catch (Exception ex) {
            LOG.info("\nError in findAllVideoCards method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    public List<VideoCardDto> copyVideoCardsToDto(List<VideoCard> videoCards) {
        LOG.info("\n** Entered copyVideoCardsToDto method with list size: " + videoCards.size() + "**\n");

        List<VideoCardDto> listToReturn = new ArrayList<>();

        for (VideoCard currentVideoCard : videoCards) {
            VideoCardDto videoCardDtoTemp = new VideoCardDto(currentVideoCard.getVideoCardId(), currentVideoCard.getVideoCardName(), currentVideoCard.getMemory(), currentVideoCard.getPerformance());
            listToReturn.add(videoCardDtoTemp);
        }

        LOG.info("\n** Exited copyVideoCardsToDto method. **\n");
        return listToReturn;
    }

}
