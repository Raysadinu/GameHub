package com.gamehub2.gamehub.ejb.Users;

import com.gamehub2.gamehub.dto.Users.UserDto;
import com.gamehub2.gamehub.entities.Others.Cart;
import com.gamehub2.gamehub.entities.Others.Follow;
import com.gamehub2.gamehub.entities.Others.Library;
import com.gamehub2.gamehub.entities.Others.Wishlist;
import com.gamehub2.gamehub.entities.Users.User;
import com.gamehub2.gamehub.entities.Users.UserDetails;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserBean {

    @Inject
    UserDetailsBean userDetailsBean;
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        LOG.info("\n** Entering findAllUsers method **\n");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            LOG.info("\n** Exited findAllUsers method **\n");
            return copyUsersToDto(users);
        } catch (Exception ex) {
            LOG.info("\nError in findAllUsers method! "+ex.getMessage()+"\n");
            throw new EJBException(ex);
        }
    }
    public List<UserDto> copyUsersToDto(List<User> users) {
        LOG.info("\n** Entered copyUsersToDto method with list size: "+ users.size() +"**\n");

        List<UserDto> listToReturn = new ArrayList<>();

        for(User currentUser : users) {
            UserDto userDtoTemp = new UserDto(currentUser.getUsername(), currentUser.getEmail(), currentUser.getPassword(),currentUser.getDateJoined(),currentUser.getRole());
            listToReturn.add(userDtoTemp);
        }

        LOG.info("\n** Exited copyUsersToDto method. **\n");
        return listToReturn;
    }
    public UserDto findUserByUsername(String username, List<UserDto> userList) {
        LOG.info("\n** Entered findUserByUsername method with the username: "+ username +"**\n");
        for (UserDto user : userList) {
            if (user.getUsername().equals(username)) {
                LOG.info("\n** Exiting findUserByUsername method. **\n");
                return user;
            }
        }
        LOG.info("\n** Exited findUserByUsername method. **\n");
        return null;
    }

    public void updateUser(UserDto newUser) {
        LOG.info("\n** Entered updateUser method with the new values for username: "+ newUser.getUsername() +" email: "+ newUser.getEmail() + " password: "+ newUser.getPassword() +"**\n");
        User user = entityManager.find(User.class, newUser.getUsername());
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        LOG.info("\n** Exited updateUser method. **\n");
    }

    public void deleteUser(String username) {
        LOG.info("\n** Entered deleteUser method with the username " + username + " **\n");

        try {
            User user = entityManager.find(User.class, username);
            if (user != null) {
                List<Follow> userFollows = entityManager.createQuery("SELECT f FROM Follow f WHERE f.user = :user", Follow.class)
                        .setParameter("user", user)
                        .getResultList();
                for (Follow follow : userFollows) {
                    entityManager.remove(follow);
                    LOG.info("\n** Deleted associated follow with id " + follow.getId() + " **\n");
                }
                UserDetails userDetails = entityManager.find(UserDetails.class, username);
                if (userDetails != null) {
                    entityManager.remove(userDetails);
                    LOG.info("\n** Deleted related entry from UserDetails table **\n");
                }


                entityManager.remove(user);
                LOG.info("\n** Deleted user entry with username " + username + " from User table **\n");
            } else {
                LOG.info("\n** User with username " + username + " not found **\n");
            }
        } catch (Exception ex) {
            LOG.info("\nError in deleteUser method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }

        LOG.info("\n** Exited deleteUser method **\n");
    }
    public List<String> getExistingUsernames() {

        List<UserDto> allUsers = findAllUsers();
        List<String> allUsernames = new ArrayList<String>();

        for(UserDto u: allUsers){
            allUsernames.add(u.getUsername());
        }

        return allUsernames;

    }
    public void createUser(String username, String email, String hashedPassword) {
        LOG.info("\n** Entered createUser method for username: " + username + " **\n");
        User newUser = new User();

        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setDateJoined(LocalDate.now());
        newUser.setRole("user");

        entityManager.persist(newUser);

        UserDetails ud=new UserDetails();
        ud.setUser(newUser);
        entityManager.persist(ud);

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(newUser);
        entityManager.persist(wishlist);

        Cart cart = new Cart();
        cart.setUser(newUser);
        entityManager.persist(cart);

        Library library = new Library();
        library.setUser(newUser);
        entityManager.persist(library);
        LOG.info("\n** Exited createUser method. **\n");
    }
    public List<String> findUserInSearchBar(String keyword) {
        LOG.info("\n Entered findUsersByKeyword method with keyword: " + keyword + " \n");

        List<UserDto> allUsers = findAllUsers();
        List<String> matchingUsernames = new ArrayList<>();

        for (UserDto user : allUsers) {
            if (user.getUsername().toLowerCase().contains(keyword.toLowerCase())) {
                matchingUsernames.add(user.getUsername());
            }
        }

        LOG.info("\n Exited findUsersByKeyword method. \n");
        return matchingUsernames;
    }

}

