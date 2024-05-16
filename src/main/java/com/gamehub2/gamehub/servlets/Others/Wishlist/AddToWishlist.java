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
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assume there is a method in WishlistBean to get wishlist ID by username

          HttpSession session = request.getSession();
          User user = (User) session.getAttribute("user");
          String username=user.getUsername();
        Long wishlistId = wishlistBean.getWishlistIdByUsername(username);

        // Check if wishlistId is valid
        if (wishlistId != null) {
            // Retrieve gameId from request parameter
            Long gameId = Long.parseLong(request.getParameter("gameId"));

            try {
                // Call the WishlistBean method to add the game to the wishlist
                wishlistBean.addGameToWishlist(wishlistId, gameId);

                // Redirect the user back to the wishlist page
                response.sendRedirect(request.getContextPath() + "/Home");
            } catch (Exception ex) {
                // Handle exceptions (e.g., game not found)
                LOG.severe("Error adding game to wishlist: " + ex.getMessage());
                // You can display an error message to the user or handle it as needed
            }
        } else {
            LOG.warning("Wishlist ID not found .");
            // You can display an error message to the user or handle it as needed
        }
    }
}