package com.gamehub2.gamehub.servlets.Others.Wishlist;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.WishlistBean;
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


@WebServlet(name = "DeleteFromWishlist", value = "/DeleteFromWishlist")
public class DeleteFromWishlist extends HttpServlet {


    @Inject
    WishlistBean wishlistBean;
    private static final Logger LOG = Logger.getLogger(DeleteFromWishlist.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("\n** Entered DeleteFromWishlist.doGet method with the gameId: "+request.getParameter("gameId")+request.getParameter("wishlistId")+ " to be deleted **\n");
        // Retrieve wishlistId and gameId from request parameters
        Long wishlistId = Long.parseLong(request.getParameter("wishlistId"));
        Long gameId = Long.parseLong(request.getParameter("gameId"));

        // Call the deleteGameFromWishlist method from the WishlistBean
        wishlistBean.deleteGameFromWishlist(wishlistId, gameId);
        response.sendRedirect(request.getContextPath() + "/Wishlist");

        LOG.info("\n** Exited DeleteFromWishlist.doGet method. **\n");
    }
}
