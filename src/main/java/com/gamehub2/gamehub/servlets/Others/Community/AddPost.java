package com.gamehub2.gamehub.servlets.Others.Community;

import com.gamehub2.gamehub.ejb.Other.PostBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import jakarta.servlet.http.Part;
import com.gamehub2.gamehub.Utilities.Functionalities;

@MultipartConfig
@WebServlet(name = "AddPost", value = "/Community/AddPost")
public class AddPost extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddPost.class.getName());

    @Inject
    PostBean postBean;
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered AddPostServlet doPost method **\n");
        request.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String username = user.getUsername();
            String description = request.getParameter("description");

            String selectedGameIdString = request.getParameter("selectedGameId");
            if (selectedGameIdString == null || selectedGameIdString.isEmpty()) {
                throw new IllegalArgumentException("Selected game ID is empty or null.");
            }

            Long gameId = Long.parseLong(selectedGameIdString);

            List<Functionalities.ImageData> imagesData = new ArrayList<>();

            // Get all parts (images) from the request
            for (Part part : request.getParts()) {
                if (part.getName().startsWith("postPicture")) {
                    Functionalities.ImageData imageData = Functionalities.processPicturePart(part);
                    imagesData.add(imageData);
                }
            }

            postBean.createCompletePost(username, description, gameId, imagesData);

            response.sendRedirect(request.getContextPath() + "/CommunityPost");
        } catch (Exception ex) {
            LOG.severe("\nError in AddPostServlet doPost method! " + ex.getMessage() + "\n");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the post.");
        }
    }

}


