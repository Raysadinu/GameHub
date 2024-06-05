package com.gamehub2.gamehub.ejb.Games;

import com.gamehub2.gamehub.dto.Games.GameScreenshotDto;
import com.gamehub2.gamehub.entities.Games.GameScreenshot;
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
public class GameScreenshotBean {

    private static final Logger LOG = Logger.getLogger(GameBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;


    public List<GameScreenshotDto> findScreenshotByGameId(Long gameId) {
        LOG.info("Entered findScreenshotByGameId method for gameId: " + gameId);
        try {
            TypedQuery<GameScreenshot> typedQuery = entityManager.createQuery("SELECT gs FROM GameScreenshot gs", GameScreenshot.class);
            List<GameScreenshot> screenshots = typedQuery.getResultList();

            List<GameScreenshotDto> screenshotDtos = new ArrayList<>();
            for (GameScreenshot screenshot : screenshots) {
                if(Objects.equals(screenshot.getGame().getGameId(), gameId)){
                    screenshotDtos.add(new GameScreenshotDto(screenshot.getId(), screenshot.getGame(),screenshot.getPicture()));
                }

            }
            LOG.info("Exited findScreenshotByGameId method");
            return screenshotDtos;
        } catch (Exception ex) {
            LOG.severe("Error in findScreenshotByGameId method! " + ex.getMessage());
            throw new EJBException(ex);
        }
    }

}
