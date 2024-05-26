package com.gamehub2.gamehub.ejb.Games;

import com.gamehub2.gamehub.common.Games.GameDto;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Games.GameDetails;
import com.gamehub2.gamehub.entities.Games.PriceDetails;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class GameBean {
    private static final Logger LOG = Logger.getLogger(GameBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<GameDto> findAllGames() {
        LOG.info("\n** Entering findAllGames method **\n");
        try {
            TypedQuery<Game> typedQuery = entityManager.createQuery("SELECT g FROM Game g", Game.class);
            List<Game> games = typedQuery.getResultList();
            LOG.info("\n** Exited findAllGames method **\n");
            return copyGamesToDto(games);
        } catch (Exception ex) {
            LOG.info("\nError in findAllGames method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    public List<GameDto> findGamesByCategoryIds(List<Long> categoryIds) {
        LOG.info("\n** Entered findGamesByCategoryIds method for categoryIds: " + categoryIds + " **\n");
        try {
            TypedQuery<Game> query = entityManager.createQuery(
                    "SELECT DISTINCT g FROM Game g JOIN g.categories c WHERE c.categoryId IN :categoryIds", Game.class);
            query.setParameter("categoryIds", categoryIds);
            List<Game> games = query.getResultList();
            LOG.info("\n** Exited findGamesByCategoryIds method **\n");
            return copyGamesToDto(games);
        } catch (Exception ex) {
            LOG.severe("\nError in findGamesByCategoryIds method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }
    public List<GameDto> findGamesThatContainAllCategories(List<Long> categoryIds) {
        LOG.info("\n** Entered findGamesThatContainAllCategories method for categoryIds: " + categoryIds + " **\n");
        try {
            TypedQuery<Game> query = entityManager.createQuery(
                    "SELECT DISTINCT g FROM Game g JOIN g.categories c WHERE c.categoryId IN :categoryIds GROUP BY g HAVING COUNT(DISTINCT c) = :categoryCount",
                    Game.class);
            query.setParameter("categoryIds", categoryIds);
            query.setParameter("categoryCount", categoryIds.size());

            List<Game> games = query.getResultList();

            LOG.info("\n** Exited findGamesThatContainAllCategories method **\n");
            return copyGamesToDto(games);
        } catch (Exception ex) {
            LOG.severe("\nError in findGamesThatContainAllCategories method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }


    public List<GameDto> copyGamesToDto(List<Game> games) {
        LOG.info("\n** Entered copyGamesToDto method with list size: " + games.size() + "**\n");

        List<GameDto> listToReturn = new ArrayList<>();

        for (Game currentGame : games) {
            GameDto gameDtoTemp = new GameDto(currentGame.getGameId(), currentGame.getGameName());
            listToReturn.add(gameDtoTemp);
        }

        LOG.info("\n** Exited copyGamesToDto method. **\n");
        return listToReturn;
    }

    public void deleteGame(Long gameId) {
        LOG.info("\n** Entered deleteGame method with the game ID " + gameId + " **\n");

        try {
            Game game = entityManager.find(Game.class, gameId);
            if (game != null) {

                TypedQuery<GameDetails> queryGameDetails = entityManager.createQuery("SELECT gd FROM GameDetails gd WHERE gd.game.gameId = :gameId", GameDetails.class);
                queryGameDetails.setParameter("gameId", gameId);
                List<GameDetails> gameDetailsList = queryGameDetails.getResultList();
                for (GameDetails gameDetails : gameDetailsList) {
                    entityManager.remove(gameDetails);
                }


                TypedQuery<PriceDetails> queryPriceDetails = entityManager.createQuery("SELECT pd FROM PriceDetails pd WHERE pd.gameId = :gameId", PriceDetails.class);
                queryPriceDetails.setParameter("gameId", gameId);
                List<PriceDetails> priceDetailsList = queryPriceDetails.getResultList();
                for (PriceDetails priceDetails : priceDetailsList) {
                    entityManager.remove(priceDetails);
                }


                entityManager.remove(game);
                LOG.info("\n** Deleted game entry with ID " + gameId + " from Game table **\n");
            } else {
                LOG.info("\n** Game with ID " + gameId + " not found **\n");
            }
        } catch (Exception ex) {
            LOG.info("\nError in deleteGame method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }

        LOG.info("\n** Exited deleteGame method **\n");
    }

    public void addGame(String gameName) {
        LOG.info("\n** Adding game: " + gameName + "**\n");
        Game game = new Game();
        game.setGameName(gameName);
        entityManager.persist(game);

        //Add name in GameDetails
        GameDetails gameDetails = new GameDetails();
        gameDetails.setGame(game);
        gameDetails.setGameName(gameName); // Set the game name
        entityManager.persist(gameDetails);

        LOG.info("Game added successfully: " + gameName + "**\n");
    }

    public List<GameDto> findGameInSearchBar(String keyword) {
        LOG.info("\n Entered findGameInSearchBar method with keyword: " + keyword + " \n");

        List<GameDto> allGames = findAllGames();
        List<GameDto> matchingGames = new ArrayList<>();

        String keywordLowerCase = keyword.toLowerCase();
        for (GameDto game : allGames) {
            if (game.getGameName().toLowerCase().startsWith(keywordLowerCase)) {
                matchingGames.add(game);
            }
        }

        LOG.info("\n Exited findGameInSearchBar method. \n");
        return matchingGames;
    }


}