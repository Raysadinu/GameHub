package com.gamehub2.gamehub.servlets.Admins;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Games.GameBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;


@WebServlet(name = "DeleteGame", value = "/DeleteGame")
public class DeleteGame extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DeleteGame.class.getName());
    @Inject
    GameBean gameBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("\n** Entered DeleteGame.doGet method with the gameId: "+request.getParameter("gameId")+" to be deleted **\n");

        gameBean.deleteGame(Long.valueOf(request.getParameter("gameId")));
        response.sendRedirect(request.getContextPath()+"/Games");

        LOG.info("\n** Exited DeleteGame.doGet method. **\n");
    }
}