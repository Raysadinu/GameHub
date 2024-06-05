package com.gamehub2.gamehub.dto.Games;

import com.gamehub2.gamehub.entities.Users.User;

import java.time.LocalDate;


public class CommentDto {
    private Long id;
    private Long gameId;
    private String username;
    private String content;
    private boolean recommended;
    private boolean notRecommended;
    private LocalDate createdAt;
    User user;
    public CommentDto() {
    }

    public CommentDto(Long id, Long gameId, String username, String content, boolean recommended, boolean notRecommended, LocalDate createdAt) {
        this.id = id;
        this.gameId = gameId;
        this.username = username;
        this.content = content;
        this.recommended = recommended;
        this.notRecommended = notRecommended;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public boolean isNotRecommended() {
        return notRecommended;
    }

    public void setNotRecommended(boolean notRecommended) {
        this.notRecommended = notRecommended;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}