package com.gamehub2.gamehub.ejb.Other;


import com.gamehub2.gamehub.dto.Others.NotificationDto;
import com.gamehub2.gamehub.dto.Others.WishlistDto;
import com.gamehub2.gamehub.entities.Others.Notification;
import com.gamehub2.gamehub.entities.Others.Post;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class NotificationBean {

    private static final Logger LOG = Logger.getLogger(NotificationBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    @Inject
    WishlistBean wishlistBean;



    public void save(Notification notification) {
        entityManager.persist(notification);
    }
    public List<NotificationDto> findAllNotifications(){
        LOG.info("\n Entered findAllNotifications \n");
        try {
            TypedQuery<Notification> typedQuery = entityManager.createQuery("SELECT notification FROM Notification notification", Notification.class);
            List<Notification> notifications = typedQuery.getResultList();
            notifications.sort(Comparator.comparing(Notification::getCreatedAt).reversed());
            LOG.info("\n** Exited findAllNotifications method **\n");
            return copyNotificationsToDto(notifications);
        } catch (Exception ex) {
            LOG.info("\n Error in findAllNotifications method! " + ex.getMessage() + " \n");
            throw new EJBException(ex);
        }
    }

    private List<NotificationDto> copyNotificationsToDto(List<Notification> notifications) {
        LOG.info("\n Entered copyNotificationsToDto \n");
        LOG.info("\n Entered copyNotificationsToDto method with list size of: " + notifications.size() + " \n");
        List<NotificationDto> listToReturn = new ArrayList<>();
        for (Notification notif : notifications) {
            NotificationDto notificationDto = new NotificationDto(notif.getId(), notif.getMessage(), notif.isSeen(), notif.getCreatedAt(), notif.getRecipient(), notif.getType(), notif.getPost());
            listToReturn.add(notificationDto);
        }
        LOG.info("\n Exited copyNotificationsToDto method. \n");
        return listToReturn;
    }

    public void sendFollowNotification(String usernameFrom, String usernameTo){
        LOG.info("\n Entered sendFollowNotification \n");
        User userFrom = entityManager.find(User.class, usernameFrom);
        User userTo = entityManager.find(User.class, usernameTo);
        Notification notification = new Notification();
        notification.setRecipient(userTo);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setSeen(false);
        notification.setType(Notification.NotificationType.FOLLOW);
        notification.setMessage("🎉 Exciting news!" + userFrom.getUsername() + " has started following you! Click here to connect and discover your new friend's profile.");
        userTo.getNotifications().add(notification);
        entityManager.merge(userTo);
        entityManager.persist(notification);
        LOG.info("Persisted FollowNotification with ID: " + notification.getId());
        LOG.info("\n Exited sendFollowNotification \n");
    }

    public void sendCommentOnPostNotification(String usernameFrom, String usernameTo, Long postId){
        LOG.info("\n Entered sendCommentOnPostNotification \n");
        User userFrom = entityManager.find(User.class, usernameFrom);
        User userTo = entityManager.find(User.class, usernameTo);
        Post post = entityManager.find(Post.class, postId);
        Notification notification = new Notification();
        notification.setRecipient(userTo);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setSeen(false);
        notification.setType(Notification.NotificationType.COMMENT_RECEIVED);
        notification.setMessage(usernameFrom + " has left a comment on your post! Click here to see their comment.");
        userTo.getNotifications().add(notification);
        notification.setPost(post);
        entityManager.merge(userTo);
        entityManager.persist(notification);
        LOG.info("Persisted CommentOnPostNotification with ID: " + notification.getId());
        LOG.info("\n Exited sendCommentOnPostNotification \n");
    }

    public void sendReactOnPostNotification(String usernameFrom, String usernameTo, Long postId){
        LOG.info("\n Entered sendReactOnPostNotification \n");
        User userFrom = entityManager.find(User.class, usernameFrom);
        User userTo = entityManager.find(User.class, usernameTo);
        Post post = entityManager.find(Post.class, postId);
        Notification notification = new Notification();
        notification.setRecipient(userTo);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setSeen(false);
        notification.setType(Notification.NotificationType.REACTION_TO_POST);
        notification.setMessage(usernameFrom + " has reacted to your post! Click here to see their reaction.");
        userTo.getNotifications().add(notification);
        notification.setPost(post);
        entityManager.merge(userTo);
        entityManager.persist(notification);
        LOG.info("Persisted ReactOnPostNotification with ID: " + notification.getId());
        LOG.info("\n Exited sendReactOnPostNotification \n");
    }


    public List<NotificationDto> findAllNotificationsByUsername(String username) {
        LOG.info("\n Entered findAllNotificationsByUsername \n");
        try {
            TypedQuery<Notification> typedQuery = entityManager.createQuery(
                    "SELECT n FROM Notification n WHERE n.recipient.username = :username", Notification.class);
            typedQuery.setParameter("username", username);
            List<Notification> notifications = typedQuery.getResultList();
            notifications.sort(Comparator.comparing(Notification::getCreatedAt).reversed());
            LOG.info("Fetched " + notifications.size() + " notifications for username: " + username);
            return copyNotificationsToDto(notifications);
        } catch (Exception ex) {
            LOG.info("\n Error in findAllNotificationsByUsername method! " + ex.getMessage() + " \n");
            throw new EJBException(ex);
        }
    }
    public void sendPaymentNotification(String usernameTo, String message) {
        LOG.info("\n Entered sendPaymentNotification \n");
        User userTo = entityManager.find(User.class, usernameTo);
        Notification notification = new Notification();
        notification.setRecipient(userTo);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setSeen(false);
        notification.setType(Notification.NotificationType.PAYMENT_STATUS);
        notification.setMessage(message);
        userTo.getNotifications().add(notification);
        entityManager.merge(userTo);
        entityManager.persist(notification);
        LOG.info("Persisted PaymentNotification with ID: " + notification.getId());
        LOG.info("\n Exited sendPaymentNotification \n");
    }
    public void sendSaleNotification(long gameId, String message) {
        List<WishlistDto> allWishlists = wishlistBean.findAllWishlists();
        for (WishlistDto wishlist : allWishlists) {
            if (wishlist.getGames().stream().anyMatch(game -> game.getGameId() == gameId)) {
                User user = wishlist.getUser();
                String username = user.getUsername();
                sendNotificationOfType(username, message, Notification.NotificationType.SALE);
            }
        }
    }
    private void sendNotificationOfType(String usernameTo, String message, Notification.NotificationType type) {
        LOG.info("\n Entered sendNotificationOfType for type: " + type + "\n");
        User userTo = entityManager.find(User.class, usernameTo);
        Notification notification = new Notification();
        notification.setRecipient(userTo);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setSeen(false);
        notification.setType(type);
        notification.setMessage(message);
        userTo.getNotifications().add(notification);
        entityManager.merge(userTo);
        entityManager.persist(notification);
        LOG.info("Persisted Notification with type " + type + " and ID: " + notification.getId());
        LOG.info("\n Exited sendNotificationOfType \n");
    }
    public List<NotificationDto> findUnreadNotificationsByUsername(String username) {
        LOG.info("\n Entered findUnreadNotificationsByUsername \n");
        List<NotificationDto> allNotifications = findAllNotificationsByUsername(username);
        List<NotificationDto> unreadNotifications = new ArrayList<>();
        for (NotificationDto notificationDto : allNotifications) {
            if (!notificationDto.isSeen()) {
                unreadNotifications.add(notificationDto);
            }
        }
        LOG.info("Found " + unreadNotifications.size() + " unread notifications for username: " + username);
        LOG.info("\n Exited findUnreadNotificationsByUsername \n");
        return unreadNotifications;
    }

    public List<NotificationDto> findReadNotificationsByUsername(String username) {
        LOG.info("\n Entered findReadNotificationsByUsername \n");
        List<NotificationDto> allNotifications = findAllNotificationsByUsername(username);
        List<NotificationDto> readNotifications = new ArrayList<>();
        for (NotificationDto notificationDto : allNotifications) {
            if (notificationDto.isSeen()) {
                readNotifications.add(notificationDto);
            }
        }
        LOG.info("Found " + readNotifications.size() + " read notifications for username: " + username);
        LOG.info("\n Exited findReadNotificationsByUsername \n");
        return readNotifications;
    }


    public NotificationDto findNotificationById(Long id) {
        LOG.info("\n Entered findNotificationById \n");
        Notification notification = entityManager.find(Notification.class, id);
        NotificationDto notificationDto = new NotificationDto(notification.getId(),notification.getMessage(),
                notification.isSeen(),notification.getCreatedAt(),notification.getRecipient(),notification.getType(),notification.getPost());
        LOG.info("\n Exited findNotificationById \n");
        return notificationDto;

    }

    public void markNotificationAsSeen(Long notificationId) {
        LOG.info("\n Entered markNotificationAsSeen \n");
        Notification notification = entityManager.find(Notification.class, notificationId);
        notification.setSeen(true);
        entityManager.merge(notification);
        LOG.info("\n Exited markNotificationAsSeen \n");
    }
}
