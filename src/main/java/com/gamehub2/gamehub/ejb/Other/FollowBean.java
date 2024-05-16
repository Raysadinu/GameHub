package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.common.Others.FollowDto;
import com.gamehub2.gamehub.entities.Others.Follow;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class FollowBean {
    private static final Logger LOG = Logger.getLogger(FollowBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<FollowDto> findAllFollows() {
        LOG.info("\n** Entered findAllFollows method **\n");

        try {
            TypedQuery<Follow> typedQuery = entityManager.createQuery("SELECT fl FROM Follow fl", Follow.class);
            List<Follow> Follows = typedQuery.getResultList();

            LOG.info("\n** Exited findAllFollows method **\n");
            return copyFollowToDto(Follows);

        } catch (Exception ex) {
            LOG.info("\nError in findAllFollows method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    private List<FollowDto> copyFollowToDto(List<Follow> FollowList) {

        LOG.info("\n** Entered copyFollowToDto method with list size of: " + FollowList.size() + " **\n");
        List<FollowDto> listToReturn = new ArrayList<>();

        for (Follow fl : FollowList) {
            FollowDto FollowDtoTemp = new FollowDto(fl.getId(), fl.getDateCreated(), fl.getUser(), fl.getFollowed());
            listToReturn.add(FollowDtoTemp);
        }

        LOG.info("\n** Exited copyFollowToDto method. **\n");
        return listToReturn;

    }

    public List<FollowDto> findFollowsByUser(String username, List<FollowDto> allFollows) {

        LOG.info("\n** Entered findFollowsByUser method with list size of: " + allFollows.size() + " and username: " + username + " **\n");
        List<FollowDto> listToReturn = new ArrayList<>();

        for (FollowDto fldto : allFollows) {
            if (fldto.getUser().getUsername().equals(username)) {
                listToReturn.add(fldto);
            }
        }

        LOG.info("\n** Exited findFollowsByUser method. **\n");
        return listToReturn;
    }
    public void addFollow(String username, String followedUsername) {
        LOG.info("\n** Entered addFollow method for user: " + username + ", friend: " + followedUsername + " **\n");

        try {
            
            User user = entityManager.find(User.class, username);
            User followed = entityManager.find(User.class, followedUsername);


            Follow Follow = new Follow();
            Follow.setUser(user);
            Follow.setFollowed(followed);
            Follow.setDateCreated(LocalDate.now());


            entityManager.persist(Follow);

            LOG.info("\n** Follow added successfully **\n");
        } catch (Exception ex) {
            LOG.info("\nError in addFollow method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }
    public void removeFollow(String username, String followedUsername) {
        try {

            Follow Follow = entityManager.createQuery("SELECT f FROM Follow f WHERE f.user.username = :username AND f.followed.username = :followedUsername", Follow.class)
                    .setParameter("username", username)
                    .setParameter("followedUsername", followedUsername)
                    .getSingleResult();

            entityManager.remove(Follow);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

}
