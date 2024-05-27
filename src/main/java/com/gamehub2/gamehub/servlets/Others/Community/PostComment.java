package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.common.Others.PostDto;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("\n Entered PostComment.doGet \n");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String postIdStr = req.getParameter("postId");
        Long postId = Long.parseLong(postIdStr);
        String commentContent = req.getParameter("commentContent");
        postCommentBean.addCommentToPost(postId, user.getUsername(), commentContent);


        LOG.info("\n Redirecting to viewPostComments with the postId: " + postId + "\n");
        resp.sendRedirect(req.getContextPath() + "/CommunityPost?postId=" + postId);


    }
}