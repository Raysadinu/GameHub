package com.gamehub2.gamehub.servlets.Others.Community;

import com.gamehub2.gamehub.common.Others.PostCommentDto;
import com.gamehub2.gamehub.common.Others.PostDto;
import com.gamehub2.gamehub.common.Others.PostReactionDto;
import com.gamehub2.gamehub.common.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Other.PostBean;
import com.gamehub2.gamehub.ejb.Other.PostCommentBean;
import com.gamehub2.gamehub.ejb.Other.PostReactionBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import com.gamehub2.gamehub.entities.Others.Picture;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(name = "ViewPost", value = "/ViewPost")
public class ViewPost extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ViewPost.class.getName());
    @Inject
    PostBean postBean;

    @Inject
    PostReactionBean postReactionBean;

    @Inject
    PostCommentBean postCommentBean;

    @Inject
    UserDetailsBean userDetailsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postIdStr = request.getParameter("postId");
        Long postId = Long.parseLong(postIdStr);

        LOG.info("\n Entered viewPostComments.doGet with postId: " + postIdStr);

        // Fetch the post, comments, and reactions
        PostDto post = postBean.findPostById(postId);

        // Fetch author details

        List<PostCommentDto> allComments = postCommentBean.findAllCommentsForPost(postId);
        List<PostReactionDto> allReactions = postReactionBean.findAllReactionsForPost(postId);
        Map<String, Picture> userPicturesMap = new HashMap<>();
        for (PostReactionDto reaction : allReactions) {
            UserDetailsDto userDetails = userDetailsBean.getUserDetailsByUsername(reaction.getUser().getUsername(),userDetailsBean.findAllUserDetails());
            if (userDetails != null) {
                userPicturesMap.put(reaction.getUser().getUsername(), userDetails.getProfilePicture());
            }
        }
        for (PostCommentDto comment : allComments) {
            UserDetailsDto userDetails = userDetailsBean.getUserDetailsByUsername(comment.getUser().getUsername(),userDetailsBean.findAllUserDetails());
            if (userDetails != null) {
                userPicturesMap.put(comment.getUser().getUsername(), userDetails.getProfilePicture());
            }
        }
        UserDetailsDto userDetails = userDetailsBean.getUserDetailsByUsername(post.getUser().getUsername(),userDetailsBean.findAllUserDetails());
        Picture userProfilePicture = userDetails != null ? userDetails.getProfilePicture() : null;

        request.setAttribute("post", post);
        request.setAttribute("userProfilePicture", userProfilePicture);
        request.setAttribute("userPicturesMap", userPicturesMap);
        request.setAttribute("comments", allComments);
        request.setAttribute("reactions", allReactions);
        request.getRequestDispatcher("/WEB-INF/userPages/viewPost.jsp").forward(request, response);
    }
}

