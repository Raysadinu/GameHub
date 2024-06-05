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
@WebServlet(name = "AddGameScreenshots", value = "/AddGameScreenshots")
public class AddGameScreenshots extends HttpServlet {

    @Inject
    GameBean gameBean;
    private static final Logger LOG = Logger.getLogger(AddGameScreenshots.class.getName());

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
        request.getRequestDispatcher("/WEB-INF/adminPages/addGameScreenshots.jsp").forward(request, response);

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

        // Handle screenshots upload
        Part screenshotsPart = request.getPart("screenshots");
        if (screenshotsPart != null && screenshotsPart.getSize() > 0) {
            for (Part screenshotPart : request.getParts()) {
                if (screenshotPart.getName().equals("screenshots")) {
                    String screenshotName = screenshotPart.getSubmittedFileName();
                    String screenshotFormat = screenshotPart.getContentType();
                    long screenshotSize = screenshotPart.getSize();

                    byte[] screenshotData = new byte[(int) screenshotSize];
                    try (InputStream inputStream = screenshotPart.getInputStream()) {
                        inputStream.read(screenshotData);
                    }

                    gameBean.addGameScreenshot(gameId, screenshotName, screenshotFormat, screenshotData);
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/GameProfile?gameId=" + gameId);
    }

}
