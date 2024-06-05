package com.gamehub2.gamehub.servlets.Others.Wishlist;

import com.gamehub2.gamehub.ejb.Other.WishlistBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "AddToWishlist", value = "/AddToWishlist")
public class AddToWishlist extends HttpServlet {

    @Inject
    WishlistBean wishlistBean;
    private static final Logger LOG = Logger.getLogger(AddToWishlist.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        Long wishlistId = wishlistBean.getWishlistIdByUsername(username);

        // Check if wishlistId is valid
        if (wishlistId != null) {
            // Retrieve gameId from request parameter
            Long gameId = Long.parseLong(request.getParameter("gameId"));

            try {
                wishlistBean.addGameToWishlist(wishlistId, gameId);
                response.sendRedirect(request.getContextPath() + "/Home");
            } catch (Exception ex) {
                LOG.severe("Error adding game to wishlist: " + ex.getMessage());
                // Set an attribute to display the error on the redirected page
                session.setAttribute("errorMessage", "Error adding game to wishlist: " + ex.getMessage());
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        } else {
            LOG.warning("Wishlist ID not found.");
            // Set an attribute to display the error on the redirected page
            session.setAttribute("errorMessage", "Wishlist ID not found.");
            response.sendRedirect(request.getContextPath() + "/Home");
        }
    }
}
