package com.gamehub2.gamehub.servlets.Games;

import com.gamehub2.gamehub.common.Games.GameDetailsDto;
import com.gamehub2.gamehub.common.Games.PriceDetailsDto;
import com.gamehub2.gamehub.ejb.Games.CategoryBean;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.common.Games.CategoryDto;
import com.gamehub2.gamehub.common.Games.GameDto;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Games.PriceDetailsBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "Filter", value = "/Filter")
public class Filter extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Filter.class.getName());

    @Inject
    private CategoryBean categoryBean;

    @Inject
    private GameBean gameBean;

    @Inject
    GameDetailsBean gameDetailsBean;

    @Inject
    PriceDetailsBean priceDetailsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedCategoryIds = request.getParameterValues("categoryIds");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");
        boolean discountedOnly = "on".equals(request.getParameter("discountedOnly"));
        boolean freeOnly = "on".equals(request.getParameter("freeOnly"));

        List<Long> categoryIds = selectedCategoryIds != null ?
                Arrays.stream(selectedCategoryIds).map(Long::parseLong).collect(Collectors.toList()) :
                Collections.emptyList();
        LOG.info("Number of categories selected: " + categoryIds.size());

        Double minPrice = minPriceStr != null && !minPriceStr.isEmpty() ? Double.parseDouble(minPriceStr) : null;
        Double maxPrice = maxPriceStr != null && !maxPriceStr.isEmpty() ? Double.parseDouble(maxPriceStr) : null;

        List<GameDto> games;
        List<CategoryDto> categories;

        if (categoryIds.isEmpty()) {
            games = gameBean.findAllGames();
            categories = categoryBean.findAllCategories();
            categories.sort(Comparator.comparing(CategoryDto::getCategoryName, String.CASE_INSENSITIVE_ORDER));
        } else {
            if (categoryIds.size() == 1) {
                games = gameBean.findGamesByCategoryIds(categoryIds);
            } else {
                games = gameBean.findGamesThatContainAllCategories(categoryIds);
            }
            categories = categoryBean.findAllCategories();
            categories.sort(Comparator.comparing(CategoryDto::getCategoryName, String.CASE_INSENSITIVE_ORDER));
        }

        LOG.info("Number of games found: " + games.size());

        List<GameDetailsDto> gamesDetails = gameDetailsBean.findAllGameDetails();
        List<PriceDetailsDto> priceList = priceDetailsBean.findAllPriceDetails();
        Map<Long, Double[]> gamePrices = new HashMap<>();

        for (GameDetailsDto gameDetail : gamesDetails) {
            Long gameId = gameDetail.getGameId();
            Double[] priceInfo = new Double[3];

            for (PriceDetailsDto priceDetail : priceList) {
                if (priceDetail.getGameId().equals(gameId)) {
                    if (priceDetail.getDiscount() > 0) {
                        priceInfo[0] = priceDetail.getPrice();
                        priceInfo[1] = priceDetail.getDiscount_price();
                        priceInfo[2] = priceDetail.getDiscount();
                    } else {
                        priceInfo[0] = priceDetail.getPrice();
                        priceInfo[1] = 0.0;
                        priceInfo[2] = 0.0;
                    }
                    break;
                }
            }
            gamePrices.put(gameId, priceInfo);
        }

        // Filter games by price
        games = games.stream().filter(game -> {
            Double[] prices = gamePrices.get(game.getGameId());
            Double price = prices[0];
            Double discountedPrice = prices[1];
            if (freeOnly && price != 0) {
                return false;
            }
            if (discountedOnly && discountedPrice == 0) {
                return false;
            }
            if (minPrice != null && price < minPrice) {
                return false;
            }
            return maxPrice == null || price <= maxPrice;
        }).collect(Collectors.toList());

        boolean filtersActive = !categoryIds.isEmpty() || minPrice != null || maxPrice != null || discountedOnly || freeOnly;

        request.setAttribute("games", games);
        request.setAttribute("categories", categories);
        request.setAttribute("gamePrices", gamePrices);
        request.setAttribute("selectedCategoryIds", categoryIds);
        request.setAttribute("filtersActive", filtersActive);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("discountedOnly", discountedOnly);
        request.setAttribute("freeOnly", freeOnly);

        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}
