package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.common.Others.PostDto;
import com.gamehub2.gamehub.ejb.Other.NotificationBean;
import com.gamehub2.gamehub.ejb.Other.PostBean;
import com.gamehub2.gamehub.ejb.Other.PostCommentBean;
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


@WebServlet(name = "PostComment", value = "/PostComment")
public class PostComment extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(PostComment.class.getName());
    @Inject
    PostCommentBean postCommentBean;
    @Inject
    PostBean postBean;
    @Inject
    NotificationBean notificationBean;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n Entered PostComment.doPost \n");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String postIdStr = request.getParameter("postId");
        Long postId = Long.parseLong(postIdStr);
        String commentContent = request.getParameter("commentContent");
        postCommentBean.addCommentToPost(postId, user.getUsername(), commentContent);
        PostDto thisPost=postBean.findPostById(postId);
        notificationBean.sendCommentOnPostNotification(user.getUsername(),thisPost.getUser().getUsername(),postId);
        LOG.info("\n Redirecting to viewPost with the postId: " + postId + "\n");
        response.sendRedirect(request.getContextPath() + "/ViewPost?postId=" + postId);
    }
}
