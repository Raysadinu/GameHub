package com.gamehub2.gamehub.ejb.Other;

import com.gamehub2.gamehub.dto.Others.PostDto;
import com.gamehub2.gamehub.dto.Others.PostReactionDto;
import com.gamehub2.gamehub.entities.Others.Post;
import com.gamehub2.gamehub.entities.Others.PostReaction;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PostReactionBean {

    private static final Logger LOG = Logger.getLogger(PostReactionBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;


    public List<PostReactionDto> findAllPostReactions() {
        LOG.info("\n Entered findAllPostReactions method \n");

        try{
            TypedQuery<PostReaction> typedQuery=entityManager.createQuery("SELECT postReaction FROM PostReaction postReaction", PostReaction.class);
            List<PostReaction> postReactions=typedQuery.getResultList();

            LOG.info("\n Exited findAllPostReactions method, successfully found "+ postReactions.size() +" reactions \n");
            return copyPostReactionsToDto(postReactions);

        }catch(Exception ex){
            LOG.info("\n Error in findAllPostReactions method! "+ex.getMessage()+" \n");
            throw new EJBException(ex);
        }
    }

    private List<PostReactionDto> copyPostReactionsToDto(List<PostReaction> postReactions) {
        LOG.info("\n Entered copyPostReactionsToDto method with list size of: "+postReactions.size()+" \n");
        List<PostReactionDto> listToReturn =new ArrayList<PostReactionDto>();

        for(PostReaction pr: postReactions){
            PostReactionDto prDto = new PostReactionDto(pr.getId(),pr.getReactionType(),pr.getUser(),pr.getPost());
            listToReturn.add(prDto);
        }

        LOG.info("\n Exited copyPostReactionsToDto method. \n");

        return listToReturn;
    }
    public PostDto findPostByIdWithDetails(Long postId) {
        // Fetch the post details along with reactions and comments
        Post post = entityManager.find(Post.class, postId);
        if (post == null) {
            return null;
        }

        PostDto postDto = new PostDto(post.getPostId(), post.getDescription(), post.getPostingDate(),
                post.getGame(), post.getUser(), post.getPostPictures(),
                post.getComments(), post.getReactions());

        return postDto;
    }

    public void addReactionToPost(Long postId, String usernameReacted, PostReaction.ReactionType reactionType) {

        LOG.info("\n Entered addReactionToPost method \n");
        Post post = entityManager.find(Post.class, postId);
        User user = entityManager.find(User.class, usernameReacted);
        PostReaction pr=new PostReaction();
        pr.setPost(post);
        pr.setUser(user);
        pr.setType(reactionType);
        post.getReactions().add(pr);
        entityManager.persist(pr);
        entityManager.merge(post);
        LOG.info("\n Exited addReactionToPost method. \n");
    }

    public List<PostReactionDto> findAllReactionsForPost(Long postId) {
        LOG.info("\n Entered findAllReactionsForPost method \n");
        List<PostReactionDto> listToReturn =new ArrayList<>();
        List<PostReactionDto> allPostReactions = findAllPostReactions();
        for(PostReactionDto pr: allPostReactions){
            if(pr.getPost().getPostId().equals(postId)){
                listToReturn.add(pr);
            }
        }
        LOG.info("\n Exited findAllReactionsForPost method. \n");
        return listToReturn;


    }
    public void removeOtherReactionsFromUser(Long postId, String username) {
        LOG.info("\n Entered removeOtherReactionsFromUser method \n");

        try {
            Post post = entityManager.find(Post.class, postId);

            if (post != null) {
                List<PostReaction> reactions = post.getReactions();

                Iterator<PostReaction> iterator = reactions.iterator();
                while (iterator.hasNext()) {
                    PostReaction reaction = iterator.next();
                    if (reaction.getUser().getUsername().equals(username)) {
                        iterator.remove();
                        entityManager.remove(reaction);
                    }
                }
                entityManager.merge(post);

                LOG.info("\n Successfully removed other reactions from user: " + username + " for post: " + postId + "\n");
            } else {
                LOG.warning("\n Post with ID: " + postId + " not found \n");
            }
        } catch (Exception ex) {
            LOG.severe("\n Error in removeOtherReactionsFromUser method: " + ex.getMessage() + "\n");
            throw new EJBException(ex);
        }
    }


}
