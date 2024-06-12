package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Others.PostDto;
import com.gamehub2.gamehub.dto.Others.PostReactionDto;
import com.gamehub2.gamehub.dto.Users.UserDetailsDto;
import com.gamehub2.gamehub.dto.Users.UserDto;
import com.gamehub2.gamehub.ejb.Other.PostBean;
import com.gamehub2.gamehub.ejb.Users.UserBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import com.gamehub2.gamehub.entities.Others.Picture;
import com.gamehub2.gamehub.entities.Users.User;
import com.gamehub2.gamehub.entities.Users.UserDetails;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@WebServlet(name = "CommunityPost", value = "/CommunityPost")
public class CommunityPost extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CommunityPost.class.getName());
    @Inject
    PostBean postBean;
    @Inject
    UserDetailsBean userDetailsBean;
    @Inject
    UserBean userBean;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered CommunityServlet doGet method **\n");
        try {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            // Find all posts
            List<PostDto> postList = postBean.findAllPosts();
            Map<String, Picture> userPicturesMap = new HashMap<>();
            for (PostDto post : postList) {
                String username = post.getUser().getUsername();
                UserDetailsDto userDetails = userDetailsBean.getUserDetailsByUsername(username, userDetailsBean.findAllUserDetails());

                if (userDetails != null && userDetails.getProfilePicture() != null) {

                    userPicturesMap.put(username, userDetails.getProfilePicture());
                }
            }


            request.setAttribute("postList", postList);
            request.setAttribute("user", user);
            request.setAttribute("postList", postList);
            request.setAttribute("userPicturesMap", userPicturesMap);
            // Forward to JSP for rendering
            request.getRequestDispatcher("/WEB-INF/userPages/community.jsp").forward(request, response);
        } catch (Exception ex) {
            LOG.severe("\nError in CommunityServlet doGet method! " + ex.getMessage() + "\n");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching posts.");
        }
    }
}
