package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.PostCommentBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;


@WebServlet(name = "DeletePostComment", value = "/DeletePostComment")
public class DeletePostComment extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DeletePostComment.class.getName());

    @Inject
    PostCommentBean postCommentBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long commentId = Long.parseLong(request.getParameter("commentId"));
            postCommentBean.deleteComment(commentId);
            response.sendRedirect(request.getContextPath() + "/CommunityPost"); // Redirect back to the community page after deletion
        } catch (NumberFormatException | EJBException ex) {
            LOG.severe("Error in deleting post comment: " + ex.getMessage());
            // Handle error appropriately, maybe display an error message or redirect back to the community page
            response.sendRedirect(request.getContextPath() + "/CommunityPost");
        }
    }
}
