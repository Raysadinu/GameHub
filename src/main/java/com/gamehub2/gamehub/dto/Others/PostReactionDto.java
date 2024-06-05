package com.gamehub2.gamehub.dto.Others;

import com.gamehub2.gamehub.entities.Others.Post;
import com.gamehub2.gamehub.entities.Others.PostReaction;
import com.gamehub2.gamehub.entities.Users.User;


public class PostReactionDto {

    Long id;
    PostReaction.ReactionType reactionType;
    User user;
    Post post;

    public PostReactionDto(Long id, PostReaction.ReactionType reactionType, User user, Post post) {
        this.id = id;
        this.reactionType = reactionType;
        this.user = user;
        this.post = post;
    }
    public PostReactionDto(PostReaction.ReactionType reactionType, User user, Post post) {
        this.reactionType = reactionType;
        this.user = user;
        this.post = post;
    }
    public PostReactionDto() {
    }

    public Long getId() {
        return id;
    }
    public PostReaction.ReactionType getReactionType() {
        return reactionType;
    }
    public User getUser() {
        return user;
    }
    public Post getPost() {
        return post;
    }
}