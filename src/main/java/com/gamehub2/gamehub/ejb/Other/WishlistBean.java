package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.common.Others.WishlistDto;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Games.PriceDetails;
import com.gamehub2.gamehub.entities.Others.Wishlist;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class WishlistBean {

    private static final Logger LOG = Logger.getLogger(WishlistBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<WishlistDto> findAllWishlists() {

        LOG.info("\n** Entered findAllWishlists method. **\n");
        try {
            TypedQuery<Wishlist> typedQuery = entityManager.createQuery("SELECT w FROM Wishlist w", Wishlist.class);
            List<Wishlist> wishlists = typedQuery.getResultList();
            LOG.info("\n** Exited findAllWishlists method with the list size of: " + wishlists.size() + "**\n");
            return copyWishlistsToDto(wishlists);
        } catch (Exception ex) {
            LOG.info("\nError in findAllWishlists method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }

    }

    private List<WishlistDto> copyWishlistsToDto(List<Wishlist> wishlists) {
        LOG.info("\n** Entered copyWishlistsToDto method with list size: " + wishlists.size() + "**\n");

        List<WishlistDto> listToReturn = new ArrayList<>();

        for (Wishlist currentWishlist : wishlists) {
            WishlistDto wishlistDtoTemp = new WishlistDto(currentWishlist.getWishlistId(), currentWishlist.getUser(), currentWishlist.getGames());
            listToReturn.add(wishlistDtoTemp);
        }

        LOG.info("\n** Exited copyWishlistsToDto method. **\n");
        return listToReturn;
    }

    public WishlistDto findWishlistByUsername(String username, List<WishlistDto> allWishlists) {
        LOG.info("\n** Entered findWishlistByUsername method with the username: " + username + "**\n");
        for (WishlistDto currentWishlist : allWishlists) {
            if (currentWishlist.getUser().getUsername().equals(username)) {
                LOG.info("\n** Exiting findWishlistByUsername method. **\n");
                return currentWishlist;
            }
        }
        LOG.info("\n** Exited findWishlistByUsername method. **\n");
        return null;
    }

    public Long getWishlistIdByUsername(String username) {
        try {
            TypedQuery<Wishlist> query = entityManager.createQuery("SELECT w FROM Wishlist w WHERE w.user.username = :username", Wishlist.class);
            query.setParameter("username", username);
            Wishlist wishlist = query.getSingleResult();
            return wishlist.getWishlistId();
        } catch (NoResultException ex) {
            // Handle case where no wishlist found for the username
            LOG.warning("No wishlist found for username: " + username);
            return null;
        } catch (Exception ex) {
            // Handle other exceptions
            LOG.severe("Error retrieving wishlist ID for username: " + username + ". Error: " + ex.getMessage());
            throw new EJBException(ex);
        }
    }
    public double getTotalPriceByWishlistId(Long wishlistId) {
        try {
            TypedQuery<Double> query = entityManager.createQuery("SELECT w.totalPrice FROM Wishlist w WHERE w.wishlistId = :wishlistId", Double.class);
            query.setParameter("wishlistId", wishlistId);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            // Handle case where no wishlist found for the wishlistId
            LOG.warning("No wishlist found for wishlistId: " + wishlistId);
            return 0.0; // Or handle differently based on your requirement
        } catch (Exception ex) {
            // Handle other exceptions
            LOG.severe("Error retrieving total price for wishlistId: " + wishlistId + ". Error: " + ex.getMessage());
            throw new EJBException(ex);
        }
    }

    public void addGameToWishlist(Long wishlistId, Long gameId) {
        try {
            // Retrieve the Wishlist and Game entities based on their IDs
            Wishlist wishlist = entityManager.find(Wishlist.class, wishlistId);
            Game game = entityManager.find(Game.class, gameId);

            if (wishlist != null && game != null) {
                // Add the game to the wishlist
                wishlist.getGames().add(game);


                // Calculate the total price including discounts, if applicable
                BigDecimal totalPrice = BigDecimal.ZERO;
                for (Game gameInWishlist : wishlist.getGames()) {
                    PriceDetails priceDetails = gameInWishlist.getPriceDetails();
                    if (priceDetails.getDiscount() > 0) {
                        totalPrice = totalPrice.add(BigDecimal.valueOf(priceDetails.getDiscount_price()));
                    } else {
                        totalPrice = totalPrice.add(BigDecimal.valueOf(priceDetails.getPrice()));
                    }
                }
                // Update the total price in the wishlist
                wishlist.setTotalPrice(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                // Save changes to the database
                entityManager.persist(wishlist);
            } else {
                // Handle case where wishlist or game is not found
                throw new EJBException("Wishlist or game not found.");
            }
        } catch (Exception ex) {
            // Handle exceptions (e.g., database errors)
            throw new EJBException("Error adding game to wishlist: " + ex.getMessage());
        }
    }

    public void deleteGameFromWishlist(Long wishlistId, Long gameId) {
        try {
            // Retrieve the Wishlist and Game entities based on their IDs
            Wishlist wishlist = entityManager.find(Wishlist.class, wishlistId);
            Game game = entityManager.find(Game.class, gameId);

            if (wishlist != null && game != null) {
                // Remove the game from the wishlist
                wishlist.getGames().remove(game);
                // Get the price details of the game
                PriceDetails priceDetails = game.getPriceDetails();

                // Subtract the price of the game from the total price of the wishlist
                BigDecimal totalPrice = BigDecimal.valueOf(wishlist.getTotalPrice())
                        .subtract(BigDecimal.valueOf(priceDetails.getPrice()));

                // Update the total price in the wishlist
                wishlist.setTotalPrice(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());



                // Save changes to the database
                entityManager.persist(wishlist);
            } else {
                // Handle case where wishlist or game is not found
                throw new EJBException("Wishlist or game not found.");
            }
        } catch (Exception ex) {
            // Handle exceptions (e.g., database errors)
            throw new EJBException("Error deleting game from wishlist: " + ex.getMessage());
        }
    }
    public boolean inWishlist(String username, Long gameId) {
        WishlistDto wishlist = findWishlistByUsername(username, findAllWishlists());
        if (wishlist != null) {
            for (Game game : wishlist.getGames()) {
                if (game.getGameId().equals(gameId)) {
                    return true;
                }
            }
        }
        return false;
    }
}

