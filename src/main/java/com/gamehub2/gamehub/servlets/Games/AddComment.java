package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Games.CommentBean;
import com.gamehub2.gamehub.entities.Games.Comment;
import com.gamehub2.gamehub.entities.Users.User;
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

import java.time.LocalDateTime;
import java.util.logging.Logger;


@WebServlet(name = "AddComment", value = "/AddComment")
public class AddComment extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddComment.class.getName());
    @Inject
    CommentBean commentBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("\n** Entered AddComment servlet **\n");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Redirect or handle unauthorized access
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String content = request.getParameter("content");
        String recommendation = request.getParameter("recommendation");
        Long gameId = Long.parseLong(request.getParameter("gameId"));

        boolean recommended = "recommended".equals(recommendation);

        commentBean.addComment(gameId, user.getUsername(), content, recommended);

        LOG.info("\n** Exited AddComment servlet **\n");
        // Redirect or forward to a success page
        response.sendRedirect(request.getContextPath() + "/GameProfile?gameId=" + gameId);
    }
}