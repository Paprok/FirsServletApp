package com.codecool.app.servlets;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleHttpServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("index.twig.html");
        JtwigModel model = JtwigModel.newModel();
        String responseStr = template.render(model);
        response.getWriter().write(responseStr);

    }
}