package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Others.PostDto;
import com.gamehub2.gamehub.ejb.Other.NotificationBean;
import com.gamehub2.gamehub.ejb.Other.PostBean;
import com.gamehub2.gamehub.ejb.Other.PostReactionBean;
import com.gamehub2.gamehub.entities.Others.PostReaction;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.logging.Logger;


@WebServlet(name = "PostReact", value = "/PostReact")
public class PostReact extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(PostReact.class.getName());

    @Inject
    PostReactionBean postReactionBean;
    @Inject
    PostBean postBean;
    @Inject
    NotificationBean notificationBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String postIdString = request.getParameter("postId");
        String reaction = request.getParameter("reaction");
        Long postId = Long.parseLong(postIdString);
        PostDto thisPost = postBean.findPostById(postId);
        LOG.info("\n Entered PostReact.doGet for the post: " + postId + " with the reaction " + reaction + " \n");

        if (reaction != null) {
            switch (reaction) {
                case "like" -> {
                    LOG.info("\n** Added like reaction to post **\n");
                    postReactionBean.removeOtherReactionsFromUser(postId, user.getUsername());
                    postReactionBean.addReactionToPost(postId, user.getUsername(), PostReaction.ReactionType.LIKE);
                    notificationBean.sendReactOnPostNotification(user.getUsername(),thisPost.getUser().getUsername(),postId);
                    response.sendRedirect(request.getContextPath() + "/CommunityPost");
                }
                case "fun" -> {
                    LOG.info("\n** Added fun reaction to post **\n");
                    postReactionBean.removeOtherReactionsFromUser(postId, user.getUsername());
                    postReactionBean.addReactionToPost(postId, user.getUsername(), PostReaction.ReactionType.FUN);
                    notificationBean.sendReactOnPostNotification(user.getUsername(),thisPost.getUser().getUsername(),postId);
                    response.sendRedirect(request.getContextPath() + "/CommunityPost");
                }
                case "helpful" -> {
                    LOG.info("\n** Added helpful reaction to post **\n");
                    postReactionBean.removeOtherReactionsFromUser(postId, user.getUsername());
                    postReactionBean.addReactionToPost(postId, user.getUsername(), PostReaction.ReactionType.HELPFUL);
                    notificationBean.sendReactOnPostNotification(user.getUsername(),thisPost.getUser().getUsername(),postId);
                    response.sendRedirect(request.getContextPath() + "/CommunityPost");
                }
                case "dislike" -> {
                    LOG.info("\n** Added dislike reaction to post **\n");
                    postReactionBean.removeOtherReactionsFromUser(postId, user.getUsername());
                    postReactionBean.addReactionToPost(postId, user.getUsername(), PostReaction.ReactionType.DISLIKE);
                    notificationBean.sendReactOnPostNotification(user.getUsername(),thisPost.getUser().getUsername(),postId);
                    response.sendRedirect(request.getContextPath() + "/CommunityPost");
                }
            }
        }
    }

}