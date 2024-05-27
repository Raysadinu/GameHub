package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.common.Others.PostDto;
import com.gamehub2.gamehub.common.Others.WishlistDto;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Games.GameScreenshot;
import com.gamehub2.gamehub.entities.Others.Media;
import com.gamehub2.gamehub.entities.Others.Picture;
import com.gamehub2.gamehub.entities.Others.Post;
import com.gamehub2.gamehub.entities.Others.Wishlist;
import com.gamehub2.gamehub.entities.Users.User;

import com.gamehub2.gamehub.entities.Users.UserDetails;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PostBean {

    private static final Logger LOG = Logger.getLogger(PostBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<PostDto> findAllPosts() {

        LOG.info("\n** Entered findAllWishlists method. **\n");
        try {
            TypedQuery<Post> typedQuery = entityManager.createQuery("SELECT p FROM Post p", Post.class);
            List<Post> posts = typedQuery.getResultList();
            LOG.info("\n** Exited findAllPosts method with the list size of: " + posts.size() + "**\n");
            return copyPostsToDto(posts);
        } catch (Exception ex) {
            LOG.info("\nError in findAllPosts method! " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }

    }

    private List<PostDto> copyPostsToDto(List<Post> posts) {
        LOG.info("\n** Entered copyPostsToDto method with list size: " + posts.size() + "**\n");

        List<PostDto> listToReturn = new ArrayList<>();

        for (Post currentPost : posts) {
            PostDto postDtoTemp = new PostDto(currentPost.getPostId(),currentPost.getDescription(),currentPost.getPostingDate(),currentPost.getGame(),currentPost.getUser(),currentPost.getPostPictures(),currentPost.getMediaPosts());
            listToReturn.add(postDtoTemp);
        }

        LOG.info("\n** Exited copyPostsToDto method. **\n");
        return listToReturn;
    }

    public List<Post> findPostsByUsername(String username) {
        LOG.info("\n** Entered findPostsByUsername method for the username: " + username + " **\n");
        try {
            TypedQuery<Post> typedQuery = entityManager.createQuery("SELECT p FROM Post p WHERE p.user.username = :username", Post.class);
            typedQuery.setParameter("username", username);
            List<Post> postList = typedQuery.getResultList();
            LOG.info("\n** Exited findPostsByUsername method **\n");
            return postList;
        } catch (Exception ex) {
            LOG.severe("\nError in findPostsByUsername method! " + ex.getMessage() + "\n");
            throw ex;
        }
    }

    public void createCompletePost(String username, String description, String videoLink, String imageName, String imageFormat, byte[] imageData) {
        User user = entityManager.find(User.class, username);
        Post post = new Post();
        post.setUser(user);
        post.setDescription(description);
        post.setPostingDate(new Date());

        if (imageName != null && imageFormat != null && imageData != null) {
            Picture picture = new Picture();
            picture.setImageName(imageName);
            picture.setImageFormat(imageFormat);
            picture.setImageData(imageData);
            picture.setPost(post);
            picture.setType(Picture.PictureType.POST);
            post.getPostPictures().add(picture);
        }

        if (videoLink != null && !videoLink.isEmpty()) {
            Media media = new Media();
            media.setLink(videoLink);
            media.setPost(post);
            media.setType(Media.MediaType.POST_VIDEO);
            post.getMediaPosts().add(media);
        }

        entityManager.persist(post);
        entityManager.flush();
    }
    public void deletePost(long postId) {
        LOG.info("\n** Entered deletePost method for postId: " + postId + " **\n");
        try {
            Post post = entityManager.find(Post.class, postId);
            if (post != null) {
                entityManager.remove(post);
                LOG.info("\n** Post with postId " + postId + " deleted successfully. **\n");
            } else {
                LOG.warning("\n** Post with postId " + postId + " not found. **\n");
            }
        } catch (Exception ex) {
            LOG.severe("\nError in deletePost method! " + ex.getMessage() + "\n");
            throw ex;
        }
    }

    public void editPost(long postId, String description, String videoLink) {
        LOG.info("\n** Entered editPost method for postId: " + postId + " **\n");
        try {
            Post post = entityManager.find(Post.class, postId);
            if (post != null) {
                post.setDescription(description);
                if (videoLink != null && !videoLink.isEmpty()) {
                    Media media = new Media();
                    media.setLink(videoLink);
                    media.setPost(post);
                    media.setType(Media.MediaType.POST_VIDEO);
                    post.getMediaPosts().add(media);
                }
                entityManager.merge(post);
                LOG.info("\n** Post with postId " + postId + " edited successfully. **\n");
            } else {
                LOG.warning("\n** Post with postId " + postId + " not found. **\n");
            }
        } catch (Exception ex) {
            LOG.severe("\nError in editPost method! " + ex.getMessage() + "\n");
            throw ex;
        }
    }


}
