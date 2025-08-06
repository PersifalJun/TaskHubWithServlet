package com.taskhub.controller;

import com.taskhub.config.PropertiesSessionFactoryProvider;
import com.taskhub.config.SessionFactoryProvider;
import com.taskhub.dao.DAO;
import com.taskhub.dao.UserDAO;
import com.taskhub.dto.UserInfo;
import com.taskhub.entity.User;
import com.taskhub.service.Service;
import com.taskhub.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

import java.util.List;


import static java.util.Objects.isNull;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {

    private final SessionFactoryProvider sessionFactoryProvider;
    private final DAO<User> dao;
    private final Service<User> service;

    public UserServlet() {
        sessionFactoryProvider = new PropertiesSessionFactoryProvider();
        dao = new UserDAO(sessionFactoryProvider.getSessionFactory());
        service = new UserService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo(); // например, /1, /new, /edit

        if (isNull(path) || path.equals("/")) {
            index(request, response);
        } else if (path.matches("/\\d+")) {
            show(request, response);
        } else if (path.matches("/\\d+/edit")) {
            edit(request, response);

        }else if (path.equals("/new")) {
            request.getRequestDispatcher("/WEB-INF/users/new.jsp").forward(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path =  request.getPathInfo();

        if (path.equals("/create")) {
            create( request, response);
        } else if (path.matches("/\\d+/update")) {
            update(request, response);
        } else if (path.matches("/\\d+/delete")) {
            delete( request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private Long extractId(HttpServletRequest request) {
        String path = request.getPathInfo();   // /5 или /5/edit
        if (isNull(path) || path.split("/").length < 2) {
            throw new IllegalArgumentException("Invalid path: " + path);
        }
        return Long.parseLong(path.split("/")[1]);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> allUsers = service.getAll();
        request.setAttribute("users", allUsers);
        request.getRequestDispatcher("/WEB-INF/users/index.jsp").forward(request, response);
    }


    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (isNull(userName) || userName.isBlank() ||
                isNull(password) || password.isBlank() ||
                isNull(email) || email.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
            return;
        }

        UserInfo info = new UserInfo(userName, password, email);
        ((UserService) service).create(info); //

        response.sendRedirect(request.getContextPath() + "/users");
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = service.getById(extractId(request));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/users/show.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = service.getById(extractId(request));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/users/edit.jsp").forward(request, response);


    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long id = extractId(request);

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserInfo info = new UserInfo(userName, password, email);
        ((UserService) service).edit(id, info);

        response.sendRedirect(request.getContextPath() + "/users");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.deleteById(extractId(request));
        response.sendRedirect(request.getContextPath() + "/users");
    }
}




