package com.codecool.app.servlets;

import com.codecool.app.helpers.PasswordGenerator;
import com.codecool.app.helpers.ServletHelper;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SimpleHttpServlet extends HttpServlet {
    private ServletHelper servletHelper = new ServletHelper();
    private PasswordGenerator passwordGenerator = new PasswordGenerator();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("index.twig.html");
        JtwigModel model = JtwigModel.newModel();
        String responseStr = template.render(model);
        response.getWriter().write(responseStr);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> parsedRequest = servletHelper.parseRequest(request);
        passwordGenerator.setAvailableSigns(parsedRequest);
        String password = passwordGenerator.getPassword();
        System.out.println(password);
        generateResponse(response, password);
    }


    private void generateResponse(HttpServletResponse response, String password) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("response.twig.html");
        JtwigModel model = JtwigModel.newModel();
        model.with("password", password);
        response.getWriter().write(template.render(model));
    }
}