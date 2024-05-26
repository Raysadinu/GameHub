package com.gamehub2.gamehub.servlets.Admins;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.CategoryDto;
import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.common.SystemReq.*;
import com.gamehub2.gamehub.ejb.Games.CategoryBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import com.gamehub2.gamehub.ejb.SystemReq.*;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "EditGame", value = "/EditGame")
public class EditGame extends HttpServlet {
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    @Inject
    CategoryBean categoryBean;
    @Inject
    MemoryBean memoryBean;
    @Inject
    ProcessorBean processorBean;
    @Inject
    VideoCardBean videoCardBean;
    @Inject
    SystemRequirementsBean systemRequirementsBean;

    private static final Logger LOG = Logger.getLogger(EditGame.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long gameId = Long.parseLong(request.getParameter("gameId"));

        LOG.info("Entering doGet method.");

        List<GameDetailsDto> gameList = gameDetailsBean.findAllGameDetails();
        List<PriceDetailsDto> priceList = priceDetailsBean.findAllPriceDetails();


        GameDetailsDto selectedGame = gameDetailsBean.getGameDetailsByGameId(gameId, gameList);
        PriceDetailsDto seletedPrice=priceDetailsBean.getPriceDetailsByGameId(gameId, priceList);

        List<CategoryDto> allCategories = categoryBean.findAllCategories();
        List<CategoryDto> gameCategories = categoryBean.getCategoriesByGameId(gameId);
        LOG.info("Forwarding to editGame.jsp.");
        request.setAttribute("allCategories", allCategories);
        request.setAttribute("gameCategories", gameCategories);
        request.setAttribute("game", selectedGame);
        request.setAttribute("price", seletedPrice);
        request.getRequestDispatcher("/WEB-INF/adminPages/editGame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storage = request.getParameter("storage");
        String publisher = request.getParameter("publisher");
        String developer = request.getParameter("developer");
        String description = request.getParameter("description");
        String releaseDateString = request.getParameter("releaseDate");
        LocalDate releaseDate = null;
        double price= Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        double discountPrice = price - (price * discount / 100);


        LOG.info("Entering doPost method.");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            releaseDate = LocalDate.parse(releaseDateString, formatter);
        } catch (DateTimeParseException e) {
            LOG.warning("Error parsing the release date: " + e.getMessage());
        }

        String gameIdString = request.getParameter("gameId");
        long gameId = Long.parseLong(gameIdString);

        GameDetailsDto newGameDetails = new GameDetailsDto(gameId, request.getParameter("gameName"), releaseDate, publisher, developer, description,storage);
        PriceDetailsDto newPriceDetails = new PriceDetailsDto(gameId,price,discount,discountPrice);


        LOG.info("Updating game details.");

        gameDetailsBean.updateGameDetails(newGameDetails);
        priceDetailsBean.updatePriceDetails(newPriceDetails);
        String[] categoryIds = request.getParameterValues("categoryIds");
        if (categoryIds != null) {
            for (String categoryIdStr : categoryIds) {
                Long categoryId = Long.parseLong(categoryIdStr);
                categoryBean.addCategoryToGame(categoryId, gameId);
            }
        }

        LOG.info("Exited doPost method.");

        response.sendRedirect(request.getContextPath() + "/GameProfile?gameId=" + gameId);
    }
}