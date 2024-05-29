package com.gamehub2.gamehub.common.Others;

import com.gamehub2.gamehub.entities.Others.Notification;
import com.gamehub2.gamehub.entities.Others.Post;
import com.gamehub2.gamehub.entities.Users.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationDto {

    Long id;
    String message;
    boolean seen;
    LocalDateTime createdAt;
    User recipient;
    Notification.NotificationType type;
    Post post;

    public NotificationDto(Long id, String message, boolean seen, LocalDateTime createdAt, User recipient, Notification.NotificationType type, Post post) {
        this.id = id;
        this.message = message;
        this.seen = seen;
        this.createdAt = createdAt;
        this.recipient = recipient;
        this.type = type;
        this.post = post;
    }
    public NotificationDto(String message, boolean seen, LocalDateTime createdAt, User recipient, Notification.NotificationType type, Post post) {
        this.message = message;
        this.seen = seen;
        this.createdAt = createdAt;
        this.recipient = recipient;
        this.type = type;
        this.post = post;
    }
    public NotificationDto() {
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return createdAt.format(formatter);
    }

    public Long getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public boolean isSeen() {
        return seen;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public User getRecipient() {
        return recipient;
    }
    public Notification.NotificationType getType() {
        return type;
    }
    public Post getPost() {
        return post;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setType(Notification.NotificationType type) {
        this.type = type;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}