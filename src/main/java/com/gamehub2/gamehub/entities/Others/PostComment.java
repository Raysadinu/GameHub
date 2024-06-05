package com.gamehub2.gamehub.entities.Others;

import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String content;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    LocalDateTime postedAt;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public LocalDateTime getPostedAt() {
        return postedAt;
    }
    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }
}
