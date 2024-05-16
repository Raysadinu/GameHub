package com.gamehub2.gamehub.servlets.Others.Cart;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.CartBean;
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


@WebServlet(name = "DeleteFromCart", value = "/DeleteFromCart")
public class DeleteFromCart extends HttpServlet {

    @Inject
    CartBean cartBean;
    private static final Logger LOG = Logger.getLogger(DeleteFromCart.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("\n** Entered DeleteFromCart.doGet method with the gameId: "+request.getParameter("gameId")+request.getParameter("wishlistId")+ " to be deleted **\n");

        Long cartId = Long.parseLong(request.getParameter("cartId"));
        Long gameId = Long.parseLong(request.getParameter("gameId"));


        cartBean.deleteGameFromCart(cartId, gameId);
        response.sendRedirect(request.getContextPath() + "/Cart");

        LOG.info("\n** Exited DeleteFromCart.doGet method. **\n");
    }

}