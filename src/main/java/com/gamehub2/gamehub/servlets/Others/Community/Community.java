package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.common.Others.PostDto;
import com.gamehub2.gamehub.ejb.Other.PostBean;
import com.gamehub2.gamehub.entities.Others.Post;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "Community", value = "/Community")
public class Community extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Community.class.getName());

    @Inject
    PostBean postBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered CommunityServlet doGet method **\n");
        try {
            // Find all posts
            List<PostDto> postList = postBean.findAllPosts();

            // Set posts as request attribute
            request.setAttribute("postList", postList);

            // Forward to JSP for rendering
            request.getRequestDispatcher("/WEB-INF/userPages/community.jsp").forward(request, response);
        } catch (Exception ex) {
            LOG.severe("\nError in CommunityServlet doGet method! " + ex.getMessage() + "\n");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching posts.");
        }
    }
}
