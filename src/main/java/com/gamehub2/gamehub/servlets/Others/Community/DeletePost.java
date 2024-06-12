package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.PostBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;


@WebServlet(name = "DeletePost", value = "/DeletePost")
public class DeletePost extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DeletePost.class.getName());
    @Inject
    PostBean postBean;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postIdParam = request.getParameter("postId");
        if (postIdParam != null) {
            try {
                long postId = Long.parseLong(postIdParam);
                LOG.info("\n** Entered DeletePost.doPost method with the postId: " + postId + " to be deleted **\n");
                postBean.deletePost(postId);
                response.sendRedirect(request.getContextPath() + "/CommunityPost");
                LOG.info("\n** Exited DeletePost.doPost method. **\n");
            } catch (NumberFormatException e) {
                LOG.severe("\nInvalid postId format: " + postIdParam + "\n");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid postId format.");
            } catch (Exception e) {
                LOG.severe("\nError occurred in DeletePost.doPost method: " + e.getMessage() + "\n");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting the post.");
            }
        } else {
            LOG.warning("\nNo postId parameter provided in request.\n");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No postId parameter provided.");
        }
    }
}

