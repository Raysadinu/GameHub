package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.common.Others.PostDto;
import com.gamehub2.gamehub.ejb.Other.PostBean;
import com.gamehub2.gamehub.ejb.Other.PostReactionBean;
import com.gamehub2.gamehub.entities.Others.PostReaction;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
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


    @Override
    protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = requset.getSession();
        User user = (User) session.getAttribute("user");
        String postIdString = requset.getParameter("postId");
        String reaction = requset.getParameter("reaction");
        Long postId = Long.parseLong(postIdString);
        PostDto thisPost = postBean.findPostById(postId);
        LOG.info("\n Entered PostReact.doGet for the post: " + postId + " with the reaction " + reaction + " \n");

        if (reaction != null) {
            switch (reaction) {
                case "like" -> {
                    LOG.info("\n** Added like reaction to post **\n");
                    postReactionBean.removeOtherReactionsFromUser(postId, user.getUsername());
                    postReactionBean.addReactionToPost(postId, user.getUsername(), PostReaction.ReactionType.LIKE);
                    response.sendRedirect(requset.getContextPath() + "/CommunityPost");
                }
                case "fun" -> {
                    LOG.info("\n** Added fun reaction to post **\n");
                    postReactionBean.removeOtherReactionsFromUser(postId, user.getUsername());
                    postReactionBean.addReactionToPost(postId, user.getUsername(), PostReaction.ReactionType.FUN);
                    response.sendRedirect(requset.getContextPath() + "/CommunityPost");
                }
                case "helpful" -> {
                    LOG.info("\n** Added helpful reaction to post **\n");
                    postReactionBean.removeOtherReactionsFromUser(postId, user.getUsername());
                    postReactionBean.addReactionToPost(postId, user.getUsername(), PostReaction.ReactionType.HELPFUL);
                    response.sendRedirect(requset.getContextPath() + "/CommunityPost");
                }
                case "dislike" -> {
                    LOG.info("\n** Added dislike reaction to post **\n");
                    postReactionBean.removeOtherReactionsFromUser(postId, user.getUsername());
                    postReactionBean.addReactionToPost(postId, user.getUsername(), PostReaction.ReactionType.DISLIKE);
                    response.sendRedirect(requset.getContextPath() + "/CommunityPost");
                }
            }
        }
    }

}