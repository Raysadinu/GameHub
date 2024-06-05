package com.gamehub2.gamehub.ejb.Games;

import com.gamehub2.gamehub.dto.Games.CommentDto;
import com.gamehub2.gamehub.entities.Games.Comment;
import com.gamehub2.gamehub.entities.Games.Game;
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
public class CommentBean {

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(CommentBean.class.getName());

    public List<CommentDto> findAllComments() {
        LOG.info("\n** Entered findAllComments method **\n");
        try {
            List<Comment> commentList = entityManager.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
            LOG.info("\n** Exited findAllComments method **\n");
            return copyCommentsToDto(commentList);
        } catch (Exception ex) {
            LOG.severe("\nError in findAllComments method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }

    private List<CommentDto> copyCommentsToDto(List<Comment> commentList) {
        LOG.info("\n** Entered copyCommentsToDto method with list size of: " + commentList.size() + " **\n");

        List<CommentDto> listToReturn = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setGameId(comment.getGame().getGameId());
            commentDto.setUsername(comment.getUser().getUsername());
            commentDto.setContent(comment.getContent());
            commentDto.setRecommended(comment.isRecommended());
            commentDto.setNotRecommended(comment.isNotRecommended());
            commentDto.setCreatedAt(comment.getCreatedAt());
            listToReturn.add(commentDto);
        }

        LOG.info("\n** Exited copyCommentsToDto method. **\n");
        return listToReturn;
    }
    public List<CommentDto> getCommentsByGameId(Long gameId) {
        LOG.info("\n** Entered getCommentsByGameId method for gameId: " + gameId + " **\n");
        try {
            TypedQuery<Comment> query = entityManager.createQuery(
                    "SELECT c FROM Comment c WHERE c.game.gameId = :gameId", Comment.class);
            query.setParameter("gameId", gameId);
            List<Comment> commentList = query.getResultList();
            LOG.info("\n** Exited getCommentsByGameId method **\n");
            return copyCommentsToDto(commentList);
        } catch (Exception ex) {
            LOG.severe("\nError in getCommentsByGameId method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }
    public void addComment(Long gameId, String username, String content, boolean recommended) {
        LOG.info("\n** Entered addComment method **\n");
        try {
            Game game = entityManager.find(Game.class, gameId);
            if (game == null) {
                LOG.severe("\nGame not found! **\n");
                return;
            }

            User user = entityManager.find(User.class, username);
            if (user == null) {
                LOG.severe("\nUser not found! **\n");
                return;
            }

            Comment comment = new Comment();
            comment.setGame(game);
            comment.setUser(user);
            comment.setContent(content);
            comment.setRecommended(recommended);
            comment.setNotRecommended(!recommended); // Set the opposite recommendation value
            comment.setCreatedAt(LocalDate.now());

            entityManager.persist(comment);

            LOG.info("\n** Successfully added comment **\n");
        } catch (Exception ex) {
            LOG.severe("\nError in addComment method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }
    public void deleteComment(Long commentId) {
        LOG.info("\n** Entered deleteComment method **\n");
        try {
            Comment comment = entityManager.find(Comment.class, commentId);
            if (comment == null) {
                LOG.severe("\nComment not found! **\n");
                return;
            }

            entityManager.remove(comment);

            LOG.info("\n** Successfully deleted comment **\n");
        } catch (Exception ex) {
            LOG.severe("\nError in deleteComment method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }
}

