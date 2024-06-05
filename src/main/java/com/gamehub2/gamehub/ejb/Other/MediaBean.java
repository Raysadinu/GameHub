package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.dto.Others.MediaDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.Media;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class MediaBean {
    private static final Logger LOG = Logger.getLogger(GameBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public void addGameVideo(Long gameId, String videoLink) {
        LOG.info("Entered addGameVideo method for gameId: " + gameId);
        try {
            // Find the existing video, if any
            Media existingVideo = findMediaByGameIdAndType(gameId, Media.MediaType.GAME_VIDEO);
            if (existingVideo != null) {
                // Delete the existing video
                deleteMedia(existingVideo.getId());
                LOG.info("Deleted existing video for gameId: " + gameId);
            }

            // Create a new Media entity for the video
            Media newVideo = new Media();
            newVideo.setLink(videoLink);
            newVideo.setGame(entityManager.find(Game.class, gameId));
            newVideo.setType(Media.MediaType.GAME_VIDEO);

            // Persist the new video
            entityManager.persist(newVideo);
            LOG.info("Added new video for gameId: " + gameId);
        } catch (Exception ex) {
            LOG.severe("Error in addGameVideo method! " + ex.getMessage());
            throw new EJBException(ex);
        }
    }

    public Media findMediaByGameIdAndType(Long gameId, Media.MediaType type) {
        LOG.info("Entered findMediaByGameIdAndType method for gameId: " + gameId + " and type: " + type);
        try {
            TypedQuery<Media> query = entityManager.createQuery(
                    "SELECT m FROM Media m WHERE m.game.gameId = :gameId AND m.type = :type", Media.class);
            query.setParameter("gameId", gameId);
            query.setParameter("type", type);

            List<Media> mediaList = query.getResultList();
            if (!mediaList.isEmpty()) {
                Media media = mediaList.get(0);
                LOG.info("Found media for gameId: " + gameId + " and type: " + type);
                return media;
            } else {
                LOG.info("No media found for gameId: " + gameId + " and type: " + type);
                return null;
            }
        } catch (Exception ex) {
            LOG.severe("Error in findMediaByGameIdAndType method! " + ex.getMessage());
            throw new EJBException(ex);
        }
    }

    public void deleteMedia(Long mediaId) {
        LOG.info("Entered deleteMedia method for mediaId: " + mediaId);
        try {
            Media media = entityManager.find(Media.class, mediaId);
            if (media != null) {
                entityManager.remove(media);
                LOG.info("Deleted media with ID " + mediaId);
            } else {
                LOG.info("Media with ID " + mediaId + " not found");
            }
        } catch (Exception ex) {
            LOG.severe("Error in deleteMedia method! " + ex.getMessage());
            throw new EJBException(ex);
        }
    }

    public MediaDto findMediaByGameId(Long gameId) {
        LOG.info("\n** Entered findMediaByGameId method for gameId: " + gameId + " **\n");
        try {
            TypedQuery<Media> query = entityManager.createQuery(
                    "SELECT m FROM Media m WHERE m.game.gameId = :gameId AND m.type = :mediaType", Media.class);
            query.setParameter("gameId", gameId);
            query.setParameter("mediaType", Media.MediaType.GAME_VIDEO);

            List<Media> mediaList = query.getResultList();

            if (!mediaList.isEmpty()) {
                Media media = mediaList.get(0);
                MediaDto mediaDto = new MediaDto(media.getId(), media.getLink());
                LOG.info("\n** Exited findMediaByGameId method **\n");
                return mediaDto;
            } else {
                LOG.warning("No media found for gameId: " + gameId);
                return null;
            }
        } catch (Exception ex) {
            LOG.severe("\nError in findMediaByGameId method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

}
