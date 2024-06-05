package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Others.PictureDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

@WebServlet(name = "GamePhotos", value = "/GamePhotos")
public class GamePhotos extends HttpServlet {

    @Inject
    GameBean gameBean;
    private static final Logger LOG = Logger.getLogger(GamePhotos.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long gameId = Long.parseLong(request.getParameter("gameId"));
            PictureDto picture = gameBean.findGameProfilePictureByGameId(gameId);
            if (picture != null) {
                LOG.info("Profile picture found for gameId: " + gameId);
                response.setContentType(picture.getImageFormat());
                response.setContentLength(picture.getImageData().length);
                response.getOutputStream().write(picture.getImageData());
            } else {
                LOG.warning("No profile picture found for gameId: " + gameId);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            LOG.severe("Invalid gameId parameter: " + request.getParameter("gameId"));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
