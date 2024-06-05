package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.dto.Games.GameDto;
import com.gamehub2.gamehub.dto.Others.LibraryDto;
import com.gamehub2.gamehub.dto.Others.WishlistDto;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.Library;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class LibraryBean {
    private static final Logger LOG = Logger.getLogger(WishlistBean.class.getName());

    @Inject
    WishlistBean wishlistBean;
    @PersistenceContext
    EntityManager entityManager;

    public List<LibraryDto> findAllLibraries(){

        LOG.info("\n** Entered findAllLibraries method. **\n");
        try {
            TypedQuery<Library> typedQuery = entityManager.createQuery("SELECT l FROM Library l", Library.class);
            List<Library> libraries = typedQuery.getResultList();
            LOG.info("\n** Exited findAllLibraries method with the list size of: "+ libraries.size() +"**\n");
            return copyLibrariesToDto(libraries);
        } catch (Exception ex) {
            LOG.info("\nError in findAllLibraries method! "+ex.getMessage()+"\n");
            throw new EJBException(ex);
        }

    }

    private List<LibraryDto> copyLibrariesToDto(List<Library> libraries) {
        LOG.info("\n** Entered copyLibrariesToDto method with list size: "+ libraries.size() +"**\n");

        List<LibraryDto> listToReturn = new ArrayList<>();

        for(Library currentLibrary : libraries) {
            LibraryDto librariesDtoTemp = new LibraryDto(currentLibrary.getLibraryId(),currentLibrary.getUser(),currentLibrary.getGames());
            listToReturn.add(librariesDtoTemp);
        }

        LOG.info("\n** Exited copyLibrariesToDto method. **\n");
        return listToReturn;
    }

    public LibraryDto findLibraryByUsername(String username, List<LibraryDto> allLibraries){
        LOG.info("\n** Entered findLibraryByUsername method with the username: "+ username +"**\n");
        for (LibraryDto currentLibrary : allLibraries) {
            if (currentLibrary.getUser().getUsername().equals(username)) {
                LOG.info("\n** Exiting findLibraryByUsername method. **\n");
                return currentLibrary;
            }
        }
        LOG.info("\n** Exited findLibraryByUsername method. **\n");
        return null;
    }
    public Long getLibraryIdByUsername(String username) {
        try {
            TypedQuery<Library> query = entityManager.createQuery("SELECT l FROM Library l WHERE l.user.username = :username", Library.class);
            query.setParameter("username", username);
            Library library = query.getSingleResult();
            return library.getLibraryId();
        } catch (NoResultException ex) {

            LOG.warning("No library found for username: " + username);
            return null;
        } catch (Exception ex) {
            // Handle other exceptions
            LOG.severe("Error retrieving library ID for username: " + username + ". Error: " + ex.getMessage());
            throw new EJBException(ex);
        }
    }
    public void addGameToLibrary(Long libraryId, Long gameId) {
        try {

            Library library = entityManager.find(Library.class, libraryId);
            Game game = entityManager.find(Game.class, gameId);

            if (library != null && game != null) {

                library.getGames().add(game);


                entityManager.persist(library);
                String username = library.getUser().getUsername();
                WishlistDto wishlist = wishlistBean.findWishlistByUsername(username, wishlistBean.findAllWishlists());
                if (wishlist != null) {

                    if (wishlistBean.inWishlist(username, gameId)) {
                        wishlistBean.deleteGameFromWishlist(wishlist.getWishlistId(), gameId);
                    }
                }
            } else {

                throw new EJBException("Library or game not found.");
            }
        } catch (Exception ex) {

            throw new EJBException("Error adding game to library: " + ex.getMessage());
        }
    }

    public boolean inLibrary(String username, Long gameId) {
        LibraryDto library = findLibraryByUsername(username, findAllLibraries());
        if (library != null) {
            for (Game game : library.getGames()) {
                if (game.getGameId().equals(gameId)) {
                    return true;
                }
            }
        }
        return false;
    }
    public List<GameDto> findGamesByUsername(String username) {
        LOG.info("\n** Entered findGamesByUsername method for username: " + username + " **\n");
        try {
            LibraryDto libraryDto = findLibraryByUsername(username, findAllLibraries());
            if (libraryDto != null) {
                List<Game> games = libraryDto.getGames();
                List<GameDto> gameDtos = new ArrayList<>();
                for (Game game : games) {
                    GameDto gameDto = new GameDto(game.getGameId(), game.getGameName());
                    gameDtos.add(gameDto);
                }
                LOG.info("\n** Exited findGamesByUsername method **\n");
                return gameDtos;
            } else {
                LOG.warning("\nNo library found for username: " + username + "\n");
                return new ArrayList<>(); // Return an empty list if the library is not found
            }
        } catch (Exception ex) {
            LOG.severe("\nError in findGamesByUsername method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }


}
