package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.common.Others.PostCommentDto;
import com.gamehub2.gamehub.entities.Games.Comment;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.Post;
import com.gamehub2.gamehub.entities.Others.PostComment;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PostCommentBean {

    private static final Logger LOG = Logger.getLogger(PostCommentBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    private List<PostCommentDto> copyPostCommentsToDto(List<PostComment> postComments) {
        LOG.info("Entered copyPostCommentsToDto method with list size of: " + postComments.size());
        List<PostCommentDto> listToReturn = new ArrayList<>();

        for (PostComment pc : postComments) {
            PostCommentDto pcDto = new PostCommentDto(pc.getId(), pc.getUser(), pc.getPost(), pc.getContent(), pc.getPostedAt());
            listToReturn.add(pcDto);
        }

        LOG.info("Exited copyPostCommentsToDto method");
        return listToReturn;
    }

    public List<PostCommentDto> findAllCommentsForPost(Long postId) {
        LOG.info("Entered findAllCommentsForPost method");
        try {
            TypedQuery<PostComment> typedQuery = entityManager.createQuery(
                    "SELECT pc FROM PostComment pc WHERE pc.post.postId = :postId ORDER BY pc.postedAt DESC",
                    PostComment.class
            );
            typedQuery.setParameter("postId", postId);
            List<PostComment> postComments = typedQuery.getResultList();
            LOG.info("Exited findAllCommentsForPost method with " + postComments.size() + " comments");
            return copyPostCommentsToDto(postComments);
        } catch (Exception ex) {
            LOG.severe("Error in findAllCommentsForPost method: " + ex.getMessage());
            throw new EJBException(ex);
        }
    }
    public void addCommentToPost(Long postId, String username, String commentContent) {
        LOG.info("Entered addCommentToPost method");

        try {
            User user = entityManager.find(User.class, username);
            if (user == null) {
                throw new IllegalArgumentException("User not found with username: " + username);
            }

            Post post = entityManager.find(Post.class, postId);
            if (post == null) {
                throw new IllegalArgumentException("Post not found with ID: " + postId);
            }

            PostComment postComment = new PostComment();
            postComment.setUser(user);
            postComment.setPost(post);
            postComment.setContent(commentContent);
            postComment.setPostedAt(LocalDateTime.now());


            entityManager.persist(postComment);

            LOG.info("Exited addCommentToPost method");
        } catch (Exception ex) {
            LOG.severe("Error in addCommentToPost method: " + ex.getMessage());
            throw new EJBException(ex);
        }
    }
}
