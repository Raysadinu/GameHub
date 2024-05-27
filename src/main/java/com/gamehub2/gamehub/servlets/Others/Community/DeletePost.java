package com.gamehub2.gamehub.servlets.Others.Community;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Other.PostBean;
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


@WebServlet(name = "DeletePost", value = "/DeletePost")
public class DeletePost extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DeletePost.class.getName());
    @Inject
    PostBean postBean;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        LOG.info("\n** Entered DeletePost.doGet method with the postId: "+request.getParameter("postId")+" to be deleted **\n");

        postBean.deletePost(Long.parseLong(request.getParameter("postId")));
        response.sendRedirect(request.getContextPath() + "/Community");

        LOG.info("\n** Exited DeletePost.doGet method. **\n");
    }
}