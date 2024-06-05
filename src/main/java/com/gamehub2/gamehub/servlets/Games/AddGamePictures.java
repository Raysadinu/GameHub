package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;
import com.gamehub2.gamehub.dto.Games.GameDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.InputStream;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet(name = "AddGamePictures", value = "/AddGamePictures")
public class AddGamePictures extends HttpServlet {

    @Inject
    GameBean gameBean;
    private static final Logger LOG = Logger.getLogger(AddGamePictures.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameIdParam = request.getParameter("gameId");
        Long gameId = null;
        try {
            gameId = Long.valueOf(gameIdParam);
        } catch (NumberFormatException e) {
            LOG.severe("Invalid gameId parameter: " + gameIdParam);
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        GameDto game = gameBean.findGameById(gameId);
        request.setAttribute("game", game);
        request.getRequestDispatcher("/WEB-INF/adminPages/addGamePictures.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long gameId = null;
        try {
            gameId = Long.valueOf(request.getParameter("gameId"));
        } catch (NumberFormatException e) {
            LOG.severe("Invalid gameId parameter: " + request.getParameter("gameId"));
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        // Handle profile picture upload
        Part profilePicturePart = request.getPart("profilePicture");
        if (profilePicturePart == null || profilePicturePart.getSize() == 0) {
            LOG.severe("No profile picture part found or file is empty");
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        String profileImageName = profilePicturePart.getSubmittedFileName();
        String profileImageFormat = profilePicturePart.getContentType();
        long profileImageSize = profilePicturePart.getSize();

        byte[] profileImageData = new byte[(int) profileImageSize];
        try (InputStream inputStream = profilePicturePart.getInputStream()) {
            inputStream.read(profileImageData);
        }

        gameBean.addGameProfilePicture(gameId, profileImageName, profileImageFormat, profileImageData);


        response.sendRedirect(request.getContextPath() + "/GameProfile?gameId=" + gameId);
    }

}
