package com.gamehub2.gamehub.servlets.Others;

import java.io.IOException;

import com.gamehub2.gamehub.dto.Games.GameDetailsDto;
import com.gamehub2.gamehub.dto.Games.GameDto;
import com.gamehub2.gamehub.dto.Others.LibraryDto;
import com.gamehub2.gamehub.ejb.Games.GameBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
import com.gamehub2.gamehub.ejb.Other.LibraryBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "Library", value = "/Library")
public class Library extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Library.class.getName());

    @Inject
    GameBean gameBean;
    @Inject
    GameDetailsBean gameDetailsBean;
    @Inject
    LibraryBean libraryBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered Library.doGet ... **\n");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<LibraryDto> allLibraries=libraryBean.findAllLibraries();
        LibraryDto library= libraryBean.findLibraryByUsername(user.getUsername(),allLibraries);
        request.setAttribute("library",library);

        String username=library.getUser().getUsername();

        List<GameDto> gamesInLibrary = gameBean.copyGamesToDto(library.getGames());
        List<GameDetailsDto> gameDetailsList = gameDetailsBean.findAllGameDetails();

        List<Long> gameIdList = new ArrayList<>();
        for(GameDto game: gamesInLibrary){
            gameIdList.add(game.getGameId());
        }

        List<GameDetailsDto> gameDetailsForGamesInLibrary = new ArrayList<>();
        for(GameDetailsDto game: gameDetailsList){
            if(gameIdList.contains(game.getGameId())){
                gameDetailsForGamesInLibrary.add(game);
            }
        }

        request.setAttribute("games",gameDetailsForGamesInLibrary);
        request.setAttribute("username",username);

        LOG.info("\n** Exited Wishlist.doGet ... **\n");
        request.getRequestDispatcher("/WEB-INF/userPages/library.jsp").forward(request,response);
    }

}