package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.Utilities.Functionalities;
import com.gamehub2.gamehub.dto.Others.PostDto;
import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.*;
import com.gamehub2.gamehub.entities.Users.User;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.*;
import java.util.logging.Logger;

@Stateless
public class PostBean {

    private static final Logger LOG = Logger.getLogger(PostBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;
    public List<PostDto> findAllPosts(){
        LOG.info("\n Entered findAllPosts method \n");

        try{
            TypedQuery<Post> typedQuery=entityManager.createQuery("SELECT post FROM Post post", Post.class);
            List<Post> posts=typedQuery.getResultList();

            LOG.info("\n Exited findAllPosts method, successfully found "+ posts.size() +" posts \n");
            return copyPostToDto(posts);

        }catch(Exception ex){
            LOG.info("\n Error in findAllPosts method! "+ex.getMessage()+" \n");
            throw new EJBException(ex);
        }

    }

    private List<PostDto> copyPostToDto(List<Post> posts) {
        LOG.info("\n Entered copyPostToDto method with list size of: " + posts.size() + " \n");
        List<PostDto> listToReturn = new ArrayList<>();
        PostDto postDtoTemp;

        for (Post p : posts) {
            postDtoTemp = new PostDto(p.getPostId(),p.getDescription(),p.getPostingDate(),p.getGame(),p.getUser(),p.getPostPictures(),p.getComments(),p.getReactions());

            listToReturn.add(postDtoTemp);
        }

        LOG.info("\n Exited copyPostToDto method. \n");
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
    public PostDto findPostById(Long postId) {

        List<PostDto> allPosts=findAllPosts();
        for(PostDto postDto:allPosts){
            if(postDto.getPostId().equals(postId)){
                return postDto;
            }
        }

        return null;
    }
    public List<PostDto> findPostsByUser(String username) {

        LOG.info("\n Entered findPostsByUser method for the user: "+username+" \n");
        List<PostDto> posts=new ArrayList<>();
        List<PostDto> allPosts=findAllPosts();
        for(PostDto postDto:allPosts){
            if(postDto.getUser().getUsername().equals(username)){
                posts.add(postDto);
            }
        }

        LOG.info("\n Exited findPostsByUser method. \n");
        return posts;

    }

    public void createCompletePost(String username, String description, Long gameId, List<Functionalities.ImageData> imagesData) {
        User user = entityManager.find(User.class, username);
        Post post = new Post();
        post.setUser(user);
        post.setDescription(description);
        post.setPostingDate(new Date());

        if (gameId != null) {
            Game game = entityManager.find(Game.class, gameId);
            if (game != null) {
                post.setGame(game);
            }
        }

        // Handle images (if present)
        for (Functionalities.ImageData imageData : imagesData) {
            if (imageData != null && imageData.getImageName() != null && imageData.getImageFormat() != null && imageData.getImageData() != null) {
                Picture picture = new Picture();
                picture.setImageName(imageData.getImageName());
                picture.setImageFormat(imageData.getImageFormat());
                picture.setImageData(imageData.getImageData());
                picture.setPost(post);
                picture.setType(Picture.PictureType.POST);
                post.getPostPictures().add(picture);
            }
        }

        entityManager.persist(post);
        entityManager.flush();
    }



    public void deletePost(long postId) {
        LOG.info("\n** Entered deletePost method for postId: " + postId + " **\n");
        try {
            Post post = entityManager.find(Post.class, postId);
            if (post != null) {
                List<Notification> notifications = entityManager.createQuery(
                                "SELECT n FROM Notification n WHERE n.post.postId = :postId", Notification.class)
                        .setParameter("postId", postId)
                        .getResultList();
                for (Notification notification : notifications) {
                    entityManager.remove(notification);
                }
                for (Picture picture : post.getPostPictures()) {
                    entityManager.remove(picture);
                }

                for (PostComment comment : post.getComments()) {
                    entityManager.remove(comment);
                }

                for (PostReaction reaction : post.getReactions()) {
                    entityManager.remove(reaction);
                }

                entityManager.remove(post);
                LOG.info("\n** Post with postId " + postId + " and associated content deleted successfully. **\n");
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
