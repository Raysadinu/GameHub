package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import com.gamehub2.gamehub.Utilities.Functionalities;
import com.gamehub2.gamehub.ejb.Other.PostBean;

import com.gamehub2.gamehub.entities.Users.User;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@MultipartConfig
@WebServlet(name = "AddPost", value = "/Community/AddPost")
public class AddPost extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddPost.class.getName());

    @Inject
    PostBean postBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered AddPostServlet doPost method **\n");
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String username = user.getUsername();
            String description = request.getParameter("description");
            String videoLink = request.getParameter("videoLink");

            Part picturePart = request.getPart("postPicture");
            Functionalities.ImageData imageData = Functionalities.processPicturePart(picturePart);

            // Create the complete post
            postBean.createCompletePost(username, description, videoLink, imageData.getImageName(), imageData.getImageFormat(), imageData.getImageData());

            response.sendRedirect(request.getContextPath() + "/Community");
        } catch (Exception ex) {
            LOG.severe("\nError in AddPostServlet doPost method! " + ex.getMessage() + "\n");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the post.");
        }
    }

}
