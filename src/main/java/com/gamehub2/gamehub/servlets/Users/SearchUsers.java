package com.gamehub2.gamehub.servlets.Users;

import java.io.IOException;

import com.gamehub2.gamehub.ejb.Users.UserBean;
import com.gamehub2.gamehub.entities.Users.User;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "SearchUsers", value = "/SearchUsers")
public class SearchUsers extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SearchUsers.class.getName());

    @Inject
    UserBean userBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Exclude the current user from the search suggestions
        List<String> suggestions = userBean.findUserInSearchBar(keyword);
        suggestions.remove(user.getUsername()); // Remove current user if present

        // Convert suggestions to JSON format
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (String username : suggestions) {
            jsonArrayBuilder.add(username);
        }
        JsonArray jsonArray = jsonArrayBuilder.build();

        // Send JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonArray.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }


}