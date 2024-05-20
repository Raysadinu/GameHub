package com.gamehub2.gamehub.servlets.Games;

import java.io.IOException;

import com.gamehub2.gamehub.common.Games.CategoryDto;
import com.gamehub2.gamehub.ejb.Games.CategoryBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "SearchCategory", value = "/SearchCategory")
public class SearchCategory extends HttpServlet {

    @Inject
    CategoryBean categoryBean;
    private static final Logger LOG = Logger.getLogger(SearchCategory.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        List<CategoryDto> suggestions = categoryBean.findCategoryByKeyword(keyword);

        // Convert suggestions to JSON format
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (CategoryDto category : suggestions) {
            JsonObjectBuilder categoryObjectBuilder = Json.createObjectBuilder()
                    .add("categoryId", category.getCategoryId())
                    .add("categoryName", category.getCategoryName());
            jsonArrayBuilder.add(categoryObjectBuilder);
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