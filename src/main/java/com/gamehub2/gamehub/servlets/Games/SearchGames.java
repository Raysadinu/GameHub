package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.gamehub2.gamehub.Utilities.Functionalities;
import com.gamehub2.gamehub.common.Games.GameDto;
import com.gamehub2.gamehub.common.Users.UserDetailsDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Games.GamePGBean;
import com.gamehub2.gamehub.ejb.Users.UserDetailsBean;
import com.gamehub2.gamehub.entities.Games.GamePG;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SearchGames", value = "/SearchGames")
public class SearchGames extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SearchGames.class.getName());

    @Inject
    GameBean gameBean;

    @Inject
    GamePGBean gamePGBean;
    @Inject
    UserDetailsBean userDetailsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        UserDetailsDto userDetails = userDetailsBean.getUserDetailsByUsername(user.getUsername(), userDetailsBean.findAllUserDetails());
        int userAge;
        if(userDetails.getBirthDate()!=null){
            userAge = Functionalities.calculateAge(userDetails.getBirthDate());
        }else
        {
            userAge = -1;
        }

        List<GamePG.PGType> validPG = Functionalities.getValidPG(userAge);
        List<GameDto> suggestions = gameBean.findGameInSearchBar(keyword);
        // Filter suggestions based on valid PG ratings
        List<GameDto> filteredSuggestions = suggestions.stream()
                .filter(game -> validPG.contains(gamePGBean.getPGTypeByGameId(game.getGameId())))
                .toList();

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (GameDto game : filteredSuggestions) {
            JsonObjectBuilder gameObjectBuilder = Json.createObjectBuilder()
                    .add("gameId", game.getGameId())
                    .add("gameName", game.getGameName());
            jsonArrayBuilder.add(gameObjectBuilder);
        }
        JsonArray jsonArray = jsonArrayBuilder.build();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonArray.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
