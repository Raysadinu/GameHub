package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.common.Others.PostCommentDto;
import com.gamehub2.gamehub.entities.Others.Post;
import com.gamehub2.gamehub.entities.Others.PostComment;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PostCommentBean {

    private static final Logger LOG = Logger.getLogger(PostReactionBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<PostCommentDto> findAllComments() {
        LOG.info("\n Entered findAllComments method \n");

        try {
            TypedQuery<PostComment> typedQuery = entityManager.createQuery("SELECT postComment FROM PostComment postComment ORDER BY postComment.postedAt DESC", PostComment.class);
            List<PostComment> postComments = typedQuery.getResultList();

            LOG.info("\n Exited findAllComments method, successfully found " + postComments.size() + " comments \n");
            return copyPostCommentsToDto(postComments);

        } catch (Exception ex) {
            LOG.info("\n Error in findAllComments method! " + ex.getMessage() + " \n");
            throw new EJBException(ex);
        }
    }

    private List<PostCommentDto> copyPostCommentsToDto(List<PostComment> postComments) {
        LOG.info("\n Entered copyPostCommentsToDto method with list size of: " + postComments.size() + " \n");
        List<PostCommentDto> listToReturn = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (PostComment pc : postComments) {
            LocalDateTime formattedDate = null;
            if (pc.getPostedAt() != null) {
                formattedDate = pc.getPostedAt();
            }
            String formattedDateString = "";
            if (formattedDate != null) {
                formattedDateString = formattedDate.format(formatter);
            }

            formattedDateString = formattedDateString.replace("T", " ");

            LocalDateTime parsedDate = null;
            if (!formattedDateString.isEmpty()) {
                parsedDate = LocalDateTime.parse(formattedDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            PostCommentDto pcDto = new PostCommentDto(pc.getId(), pc.getContent(), parsedDate, pc.getUser(), pc.getPost());
            listToReturn.add(pcDto);
        }

        LOG.info("\n Exited copyPostCommentsToDto method. \n");

        return listToReturn;
    }

    public List<PostCommentDto> findAllCommentsForPost(Long postId) {
        LOG.info("\n Entered findAllCommentsForPost method \n");
        List<PostCommentDto> listToReturn = new ArrayList<>();
        List<PostCommentDto> allComments = findAllComments();
        for (PostCommentDto pc : allComments) {
            if (pc.getPost().getPostId().equals(postId)) {
                listToReturn.add(pc);
            }
        }
        LOG.info("\n Exited findAllCommentsForPost method. \n");
        return listToReturn;
    }
    public void addCommentToPost(Long postId, String authorUsername, String commentContent) {
        LOG.info("\n Entered addCommentToPost method \n");

        User user = entityManager.find(User.class, authorUsername);

        Post post = entityManager.find(Post.class, postId);

        PostComment postComment = new PostComment();
        postComment.setContent(commentContent);
        postComment.setPostedAt(LocalDateTime.now());
        postComment.setUser(user);
        postComment.setPost(post);

        post.getComments().add(postComment);
        entityManager.persist(postComment);

        entityManager.merge(post);

        LOG.info("\n Exited addCommentToPost method. \n");
    }
}