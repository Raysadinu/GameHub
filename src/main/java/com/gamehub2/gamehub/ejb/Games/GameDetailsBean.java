package com.gamehub2.gamehub.ejb.Games;

import com.gamehub2.gamehub.common.Games.CategoryDto;
import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.GameDto;
import com.gamehub2.gamehub.entities.Games.Category;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Games.GameDetails;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class GameDetailsBean {

    private static final Logger LOG = Logger.getLogger(GameDetailsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;


    public List<GameDetailsDto> findAllGameDetails() {
        LOG.info("\n** Entered findAllGameDetails method **\n");
        try {
            TypedQuery<GameDetails> typedQuery = entityManager.createQuery("SELECT gd FROM GameDetails gd", GameDetails.class);
            List<GameDetails> gameDetailsList = typedQuery.getResultList();
            LOG.info("\n** Exited findAllGameDetails method **\n");
            return copyGameDetailsToDto(gameDetailsList);
        } catch (Exception ex) {
            LOG.severe("\nError in findAllGameDetails method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    private List<GameDetailsDto> copyGameDetailsToDto(List<GameDetails> gameDetailsList) {
        LOG.info("\n** Entered copyGameDetailsToDto method with list size of: " + gameDetailsList.size() + " **\n");

        List<GameDetailsDto> listToReturn = new ArrayList<>();

        for (GameDetails gd : gameDetailsList) {
            GameDetailsDto gameDetailsDtoTemp = new GameDetailsDto(gd.getGameId(), gd.getGame().getGameName(),gd.getReleaseDate(), gd.getPublisher(), gd.getDeveloper(), gd.getDescription(), gd.getStorage());
            listToReturn.add(gameDetailsDtoTemp);
        }

        LOG.info("\n** Exited copyGameDetailsToDto method. **\n");
        return listToReturn;
    }

    public GameDetailsDto getGameDetailsByGameId(Long gameId, List<GameDetailsDto> gameDetailsDtoList) {
        LOG.info("\n** Entered getGameDetailsByGameId method for the gameId: " + gameId + " and with a list size of: " + gameDetailsDtoList.size() + " **\n");

        GameDetailsDto gameToReturn = null;

        for (GameDetailsDto gdd : gameDetailsDtoList) {
            if (Objects.equals(gdd.getGameId(), gameId)) {
                gameToReturn = gdd;
            }
        }

        if (gameToReturn == null) {
            LOG.info("\n** No game returning NULL GAME **\n");
            gameToReturn = new GameDetailsDto(Long.parseLong("-1"),null,LocalDate.now(),null,null,null,null);
        }

        LOG.info("\n** Exited getGameDetailsByGameId method. **\n");
        return gameToReturn;
    }


    public void updateGameDetails(GameDetailsDto newGameDetails) {
        LOG.info("\n** Entered updateGameDetails method for gameId: " + newGameDetails.getGameId() + " **\n");

        GameDetails gameDetails = entityManager.find(GameDetails.class, newGameDetails.getGameId());
        if (gameDetails == null) {
            throw new EJBException("GameDetails with ID: " + newGameDetails.getGameId() + " not found.");
        }
        gameDetails.setReleaseDate(newGameDetails.getReleaseDate());
        gameDetails.setPublisher(newGameDetails.getPublisher());
        gameDetails.setDeveloper(newGameDetails.getDeveloper());
        gameDetails.setDescription(newGameDetails.getDescription());
        gameDetails.setStorage(newGameDetails.getStorage());

        LOG.info("\n** Exited updateGameDetails method. **\n");
    }



}
