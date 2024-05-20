package com.gamehub2.gamehub.servlets.Admins;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Games.CategoryBean;
import com.gamehub2.gamehub.ejb.Games.GameDetailsBean;
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


@WebServlet(name = "DeleteCategory", value = "/DeleteCategory")
public class DeleteCategory extends HttpServlet {

    @Inject
    CategoryBean categoryBean;
    private static final Logger LOG = Logger.getLogger(DeleteCategory.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long categoryId = Long.parseLong(request.getParameter("categoryId"));
        Long gameId = Long.parseLong(request.getParameter("gameId"));

        LOG.info("Deleting category from game.");

        categoryBean.deleteCategoryFromGame(categoryId, gameId);

        LOG.info("Category deleted from game.");




        response.sendRedirect(request.getContextPath() + "/EditGame?gameId=" + gameId);
    }
}