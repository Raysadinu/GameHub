package com.gamehub2.gamehub.entities.Others;


import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String message;
    @Basic
    private boolean seen;
    @Basic
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "recipient_username")
    private User recipient;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    public enum NotificationType {
        FOLLOW,
        REACTION_TO_POST,
        COMMENT_RECEIVED,
        PAYMENT_STATUS,
        SALE
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isSeen() {
        return seen;
    }
    public void setSeen(boolean seen) {
        this.seen = seen;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public NotificationType getType() {
        return type;
    }
    public void setType(NotificationType type) {
        this.type = type;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public User getRecipient() {
        return recipient;
    }
    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}