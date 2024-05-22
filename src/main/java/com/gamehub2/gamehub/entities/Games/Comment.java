package com.gamehub2.gamehub.entities.Games;

import com.gamehub2.gamehub.entities.Users.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(columnDefinition = "TEXT")
    @Lob
    private String content;

    private boolean recommended; // Indicates if the comment serves as a recommendation
    private boolean notRecommended; // Indicates if the comment serves as a non-recommendation

    private LocalDate createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
