package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Games.CommentBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

@WebServlet(name = "DeleteComment", value = "/DeleteComment")
public class DeleteComment extends HttpServlet {

    @Inject
    CommentBean commentBean;

    private static final Logger LOG = Logger.getLogger(DeleteComment.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the comment ID from the request parameter
        String commentIdStr = request.getParameter("commentId");
        if (commentIdStr != null && !commentIdStr.isEmpty()) {
            try {
                Long commentId = Long.parseLong(commentIdStr);

                commentBean.deleteComment(commentId);

                response.sendRedirect(request.getContextPath() + "/GameProfile?gameId=" + request.getParameter("gameId"));
            } catch (NumberFormatException ex) {
                LOG.severe("Invalid comment ID format: " + commentIdStr);

                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {

            LOG.severe("Comment ID not found in request parameters.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
