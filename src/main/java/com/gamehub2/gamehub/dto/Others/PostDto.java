package com.gamehub2.gamehub.dto.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.Picture;
import com.gamehub2.gamehub.entities.Others.PostComment;
import com.gamehub2.gamehub.entities.Others.PostReaction;
import com.gamehub2.gamehub.entities.Users.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDto {

    private Long postId;
    private String description;
    private Date postingDate;
    private Game game;
    private User user;
    private List<Picture> postPictures = new ArrayList<>();
    List<PostComment> comments;
    List<PostReaction> reactions;
    private Picture profilePicture;
    public PostDto() {
    }

    public PostDto(Long postId, String description, Date postingDate, Game game, User user, List<Picture> postPictures, List<PostComment> comments, List<PostReaction> reactions) {
        this.postId = postId;
        this.description = description;
        this.postingDate = postingDate;
        this.game = game;
        this.user = user;
        this.postPictures = postPictures;
        this.comments = comments;
        this.reactions = reactions;
    }

    public PostDto(Long postId, String description, Date postingDate, Game game, User user, List<Picture> postPictures) {
        this.postId = postId;
        this.description = description;
        this.postingDate = postingDate;
        this.game = game;
        this.user = user;
        this.postPictures = postPictures;

    }

    public PostDto(Long postId, String description, Date postingDate, Game game, User user) {
        this.postId = postId;
        this.description = description;
        this.postingDate = postingDate;
        this.game = game;
        this.user = user;
    }
    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }
    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public List<PostReaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<PostReaction> reactions) {
        this.reactions = reactions;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Picture> getPostPictures() {
        return postPictures;
    }

    public void setPostPictures(List<Picture> postPictures) {
        this.postPictures = postPictures;
    }
    public int getCommentsCount() {
        return comments != null ? comments.size() : 0;
    }
    public int getReactionsCount() {
        return reactions != null ? reactions.size() : 0;
    }
}
