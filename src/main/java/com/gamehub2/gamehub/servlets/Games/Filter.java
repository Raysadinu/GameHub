package com.gamehub2.gamehub.servlets.Games;

import com.gamehub2.gamehub.Utilities.Functionalities;
import com.gamehub2.gamehub.dto.Games.GameDetailsDto;
import com.gamehub2.gamehub.dto.Games.PriceDetailsDto;
import com.gamehub2.gamehub.dto.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Games.*;
import com.gamehub2.gamehub.dto.Games.CategoryDto;
import com.gamehub2.gamehub.dto.Games.GameDto;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.LibraryBean;
import com.gamehub2.gamehub.ejb.Other.PaymentRequestBean;
import com.gamehub2.gamehub.ejb.Other.WishlistBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import com.gamehub2.gamehub.entities.Games.GamePG;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "Filter", value = "/Filter")
public class Filter extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Filter.class.getName());
    @Inject
    CategoryBean categoryBean;
    @Inject
    GameBean gameBean;
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    PriceDetailsBean priceDetailsBean;
    @Inject
    WishlistBean wishlistBean;
    @Inject
    CartBean cartBean;
    @Inject
    LibraryBean libraryBean;
    @Inject
    UserDetailsBean userDetailsBean;
    @Inject
    GamePGBean gamePGBean;
    @Inject
    PaymentRequestBean paymentRequestBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        UserDetailsDto userDetailsDto = userDetailsBean.getUserDetailsByUsername(user.getUsername(), userDetailsBean.findAllUserDetails());
        int userAge;
        if(userDetailsDto.getBirthDate()!=null){
            userAge = Functionalities.calculateAge(userDetailsDto.getBirthDate());
        }else
        {
            userAge = -1;
        }

        String[] selectedCategoryIds = request.getParameterValues("categoryIds");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");
        boolean discountedOnly = "on".equals(request.getParameter("discountedOnly"));
        boolean freeOnly = "on".equals(request.getParameter("freeOnly"));

        List<GameDto> games;
        List<CategoryDto> categories;
        List<GameDetailsDto> gamesDetails = gameDetailsBean.findAllGameDetails();
        List<GameDto> gameDetailsOnWishlist = new ArrayList<>();
        List<GameDto> gameDetailsInCart = new ArrayList<>();
        List<GameDto> gameDetailsInLibrary = new ArrayList<>();
        List<GameDto> gameDetailsPendingPayment = new ArrayList<>();

        List<Long> categoryIds = selectedCategoryIds != null ?
                Arrays.stream(selectedCategoryIds).map(Long::parseLong).collect(Collectors.toList()) :
                Collections.emptyList();
        LOG.info("Number of categories selected: " + categoryIds.size());

        Double minPrice = minPriceStr != null && !minPriceStr.isEmpty() ? Double.parseDouble(minPriceStr) : null;
        Double maxPrice = maxPriceStr != null && !maxPriceStr.isEmpty() ? Double.parseDouble(maxPriceStr) : null;
        if (categoryIds.isEmpty() && minPrice == null && maxPrice == null && !discountedOnly && !freeOnly) {
            response.sendRedirect(request.getContextPath() + "/Home");
            return;
        }

        if (categoryIds.isEmpty()) {
            games = gameBean.findAllGames();
        } else {
            if (categoryIds.size() == 1) {
                games = gameBean.findGamesByCategoryIds(categoryIds);
            } else {
                games = gameBean.findGamesThatContainAllCategories(categoryIds);
            }
        }
        categories = categoryBean.findAllCategories();
        categories.sort(Comparator.comparing(CategoryDto::getCategoryName, String.CASE_INSENSITIVE_ORDER));


        List<PriceDetailsDto> priceList = priceDetailsBean.findAllPriceDetails();
        Map<Long, Double[]> gamePrices = Functionalities.gamePrices(gamesDetails, priceList);
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
        LOG.info("Number of games found: " + games.size());


        boolean filtersActive = !categoryIds.isEmpty() || minPrice != null || maxPrice != null || discountedOnly || freeOnly;


        for (GameDto game : games) {
            boolean inWishlist = wishlistBean.inWishlist(user.getUsername(), game.getGameId());
            boolean inLibrary = libraryBean.inLibrary(user.getUsername(), game.getGameId());
            boolean inCart = cartBean.inCart(user.getUsername(), game.getGameId());
            boolean pendingPayment = paymentRequestBean.isPendingPayment(user.getUsername(), game.getGameId());

            if (inWishlist) {
                gameDetailsOnWishlist.add(game);
            }
            if (inCart) {
                gameDetailsInCart.add(game);
            }
            if (inLibrary) {
                gameDetailsInLibrary.add(game);
            }
            if (pendingPayment) {
                gameDetailsPendingPayment.add(game);
            }
        }

        List<GamePG.PGType> validPG = Functionalities.getValidPG(userAge);
        Iterator<GameDto> iterator = games.iterator();
        while (iterator.hasNext()) {
            GameDto gameDto = iterator.next();
            GamePG.PGType thisGamePG = gamePGBean.getPGTypeByGameId(gameDto.getGameId());
            if (!validPG.contains(thisGamePG)) {
                iterator.remove();
            }
        }
        LOG.info("\n**" + games.size() + "**\n");
        request.setAttribute("games", games);
        request.setAttribute("categories", categories);
        request.setAttribute("gamePrices", gamePrices);
        request.setAttribute("selectedCategoryIds", categoryIds);
        request.setAttribute("filtersActive", filtersActive);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("discountedOnly", discountedOnly);
        request.setAttribute("freeOnly", freeOnly);

        request.setAttribute("wishlist", gameDetailsOnWishlist);
        request.setAttribute("cart", gameDetailsInCart);
        request.setAttribute("library", gameDetailsInLibrary);
        request.setAttribute("pendingPayment", gameDetailsPendingPayment);
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}
