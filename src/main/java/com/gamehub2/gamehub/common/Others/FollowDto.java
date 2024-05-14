package com.gamehub2.gamehub.common.Others;

import com.gamehub2.gamehub.entities.Users.User;

import java.time.LocalDate;

public class FollowDto {

    Long id;
    LocalDate dateCreated;
    User user;
    User followed;

    public FollowDto(Long id, LocalDate dateCreated, User user, User followed) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.user = user;
        this.followed = followed;
    }

    public FollowDto() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public User getFollowed() {
        return followed;
    }
}
