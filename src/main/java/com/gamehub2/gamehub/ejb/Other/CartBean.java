package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.common.Others.CartDto;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Games.PriceDetails;
import com.gamehub2.gamehub.entities.Others.Cart;
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
public class CartBean {
    private static final Logger LOG = Logger.getLogger(WishlistBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<CartDto> findAllCarts(){

        LOG.info("\n** Entered findAllCarts method. **\n");
        try {
            TypedQuery<Cart> typedQuery = entityManager.createQuery("SELECT cr FROM Cart cr", Cart.class);
            List<Cart> carts = typedQuery.getResultList();
            LOG.info("\n** Exited findAllCarts method with the list size of: "+ carts.size() +"**\n");
            return copyCartsToDto(carts);
        } catch (Exception ex) {
            LOG.info("\nError in findAllCarts method! "+ex.getMessage()+"\n");
            throw new EJBException(ex);
        }

    }

    private List<CartDto> copyCartsToDto(List<Cart> carts) {
        LOG.info("\n** Entered copyCartsToDto method with list size: "+ carts.size() +"**\n");

        List<CartDto> listToReturn = new ArrayList<>();

        for(Cart currentCart : carts) {
            CartDto cartDtoTemp = new CartDto(currentCart.getCartId(),currentCart.getUser(),currentCart.getGames());
            listToReturn.add(cartDtoTemp);
        }

        LOG.info("\n** Exited copyCartsToDto method. **\n");
        return listToReturn;
    }

    public CartDto findCartByUsername(String username, List<CartDto> allCarts){
        LOG.info("\n** Entered findCartByUsername method with the username: "+ username +"**\n");
        for (CartDto currentCart : allCarts) {
            if (currentCart.getUser().getUsername().equals(username)) {
                LOG.info("\n** Exiting findCartByUsername method. **\n");
                return currentCart;
            }
        }
        LOG.info("\n** Exited findCartByUsername method. **\n");
        return null;
    }

    public CartDto findCartById(Long cartId, List<CartDto> allCarts){
        LOG.info("\n** Entered findCartById method with the ID: "+ cartId +"**\n");
        for (CartDto currentCart : allCarts) {
            if (currentCart.getCartId().equals(cartId)) {
                LOG.info("\n** Exiting findCartById method. **\n");
                return currentCart;
            }
        }
        LOG.info("\n** Exited findCartById method. **\n");
        return null;
    }
    public Long getCartIdByUsername(String username) {
        try {
            TypedQuery<Cart> query = entityManager.createQuery("SELECT cr FROM Cart cr WHERE cr.user.username = :username", Cart.class);
            query.setParameter("username", username);
            Cart cart = query.getSingleResult();
            return cart.getCartId();
        } catch (NoResultException ex) {

            LOG.warning("No cart found for username: " + username);
            return null;
        } catch (Exception ex) {

            LOG.severe("Error retrieving cart ID for username: " + username + ". Error: " + ex.getMessage());
            throw new EJBException(ex);
        }
    }
    public double getTotalPriceByCartId(Long cartId) {
        try {
            TypedQuery<Double> query = entityManager.createQuery("SELECT cr.totalPrice FROM Cart cr WHERE cr.cartId = :cartId", Double.class);
            query.setParameter("cartId", cartId);
            return query.getSingleResult();
        } catch (NoResultException ex) {

            LOG.warning("No cart found for cartId: " + cartId);
            return 0.0;
        } catch (Exception ex) {

            LOG.severe("Error retrieving total price for cartId: " + cartId + ". Error: " + ex.getMessage());
            throw new EJBException(ex);
        }
    }

    public void addGameToCart(Long cartId, Long gameId) {
        try {
            // Retrieve the Cart and Game entities based on their IDs
            Cart cart = entityManager.find(Cart.class, cartId);
            Game game = entityManager.find(Game.class, gameId);

            if (cart != null && game != null) {
                // Add the game to the cart (assuming a collection of games in Wishlist)
                cart.getGames().add(game);


                BigDecimal totalPrice = BigDecimal.ZERO;
                for (Game gameInCart : cart.getGames()) {
                    PriceDetails priceDetails = gameInCart.getPriceDetails();
                    if (priceDetails.getDiscount() > 0) {
                        totalPrice = totalPrice.add(BigDecimal.valueOf(priceDetails.getDiscount_price()));
                    } else {
                        totalPrice = totalPrice.add(BigDecimal.valueOf(priceDetails.getPrice()));
                    }
                }

                cart.setTotalPrice(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                entityManager.persist(cart);
            } else {

                throw new EJBException("Cart or game not found.");
            }
        } catch (Exception ex) {

            throw new EJBException("Error adding game to cart: " + ex.getMessage());
        }
    }

    public void deleteGameFromCart(Long cartId, Long gameId) {
        try {

            Cart cart = entityManager.find(Cart.class, cartId);
            Game game = entityManager.find(Game.class, gameId);

            if (cart != null && game != null) {

                cart.getGames().remove(game);

                PriceDetails priceDetails = game.getPriceDetails();

                BigDecimal totalPrice = BigDecimal.valueOf(cart.getTotalPrice())
                        .subtract(BigDecimal.valueOf(priceDetails.getPrice()));

                cart.setTotalPrice(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                entityManager.persist(cart);
            } else {
                // Handle case where wishlist or game is not found
                throw new EJBException("Wishlist or game not found.");
            }
        } catch (Exception ex) {
            // Handle exceptions (e.g., database errors)
            throw new EJBException("Error deleting game from wishlist: " + ex.getMessage());
        }
    }
    public boolean inCart(String username, Long gameId) {
        CartDto cart = findCartByUsername(username, findAllCarts());
        if (cart != null) {
            for (Game game : cart.getGames()) {
                if (game.getGameId().equals(gameId)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void clearCart(Long cartId) {
        LOG.info("\n** Enter clearCart method. **\n");
        Cart c = entityManager.find(Cart.class, cartId);
        List<Game> games = c.getGames();

        // Create a copy of the games list to avoid ConcurrentModificationException
        List<Game> gamesCopy = new ArrayList<>(games);

        // Iterate over the copy and remove games from the original list
        for (Game game : gamesCopy) {
            deleteGameFromCart(cartId, game.getGameId());
        }

        LOG.info("\n** Exited clearCart method**\n");
    }
    public List<Long> getGamesInCart(long cartId) {
        List<Long> gamesIds = new ArrayList<Long>();
        CartDto thisCart = findCartById(cartId, findAllCarts());
        for(Game g:thisCart.getGames())
        {
            gamesIds.add(g.getGameId());
        }
        return gamesIds;
    }
}
