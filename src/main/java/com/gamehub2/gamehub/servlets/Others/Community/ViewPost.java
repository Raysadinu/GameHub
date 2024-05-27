package com.gamehub2.gamehub.servlets.Others.Community;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.logging.Logger;


@WebServlet(name = "ViewPost", value = "/ViewPost")
public class ViewPost extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ViewPost.class.getName());

}