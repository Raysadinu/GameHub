package com.gamehub2.gamehub.entities.Others;

import com.gamehub2.gamehub.entities.Games.Game;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long postId;

    @Basic
    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postingDate;

    @OneToOne
    @JoinColumn(name = "gameId")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Picture> postPictures = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Media> mediaPosts = new ArrayList<>();


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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

}
