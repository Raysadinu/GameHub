package com.gamehub2.gamehub.common.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Others.Media;
import com.gamehub2.gamehub.entities.Others.Picture;
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
    private List<Media> mediaPosts = new ArrayList<>();

    public PostDto() {
    }

    public PostDto(Long postId, String description, Date postingDate, Game game, User user, List<Picture> postPictures, List<Media> mediaPosts) {
        this.postId = postId;
        this.description = description;
        this.postingDate = postingDate;
        this.game = game;
        this.user = user;
        this.postPictures = postPictures;
        this.mediaPosts = mediaPosts;
    }

    public PostDto(Long postId, String description, Date postingDate, Game game, User user) {
        this.postId = postId;
        this.description = description;
        this.postingDate = postingDate;
        this.game = game;
        this.user = user;
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

    public List<Media> getMediaPosts() {
        return mediaPosts;
    }

    public void setMediaPosts(List<Media> mediaPosts) {
        this.mediaPosts = mediaPosts;
    }
}
