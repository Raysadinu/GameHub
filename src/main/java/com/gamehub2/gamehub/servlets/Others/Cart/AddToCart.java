package com.gamehub2.gamehub.servlets.Others.Cart;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.CartBean;
import com.gamehub2.gamehub.ejb.Other.WishlistBean;
import com.gamehub2.gamehub.entities.Users.User;
import com.gamehub2.gamehub.servlets.Others.Wishlist.AddToWishlist;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.logging.Logger;


@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCart extends HttpServlet {

    @Inject
    CartBean cartBean;
    private static final Logger LOG = Logger.getLogger(AddToWishlist.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();

        Long cartId = cartBean.getCartIdByUsername(username);

        if (cartId != null) {
            Long gameId = Long.parseLong(request.getParameter("gameId"));

            try {
                cartBean.addGameToCart(cartId, gameId);


                response.sendRedirect(request.getContextPath() + "/Home");
                return;
            } catch (Exception ex) {
                LOG.severe("Error adding game to cart: " + ex.getMessage());

                request.setAttribute("errorMessage", "Error adding game to cart.");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                return;
            }
        } else {
            LOG.warning("Cart ID not found.");
            request.setAttribute("errorMessage", "Cart ID not found.");
        }


        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }


}