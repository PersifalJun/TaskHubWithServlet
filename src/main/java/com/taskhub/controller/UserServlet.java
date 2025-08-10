package com.taskhub.controller;

import com.taskhub.config.PropertiesSessionFactoryProvider;
import com.taskhub.config.SessionFactoryProvider;
import com.taskhub.dao.DAO;
import com.taskhub.dao.ProjectDAO;
import com.taskhub.dao.UserDAO;
import com.taskhub.dto.UserInfo;
import com.taskhub.entity.Project;
import com.taskhub.entity.User;
import com.taskhub.service.ProjectService;
import com.taskhub.service.Service;
import com.taskhub.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;


import java.io.IOException;

import java.util.List;


import static java.util.Objects.isNull;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {



    private final SessionFactoryProvider sessionFactoryProvider;
    private final DAO<User> userDAO;
    private final DAO<Project> projectDao;
    private final Service<User> userService;
    private final Service<Project> projectService;

    public UserServlet() {
        sessionFactoryProvider = new PropertiesSessionFactoryProvider();
        userDAO = new UserDAO(sessionFactoryProvider.getSessionFactory());
        projectDao = new ProjectDAO(sessionFactoryProvider.getSessionFactory());
        userService = new UserService(userDAO);
        projectService = new ProjectService(projectDao);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        String path = request.getPathInfo(); // например, /1, /new, /edit

        //Users
        if (isNull(path) || path.equals("/")) {
            index(request, response);
        } else if (path.matches("/\\d+")) {
            showUserProfile(request, response);
        } else if (path.matches("/\\d+/edit")) {
            editUserProfile(request, response);
        }
        else if (path.equals("/new")) {
            request.getRequestDispatcher("/WEB-INF/users/new.jsp").forward(request, response);
        }

        //Projects

        //Все проекты
        else if (path.equals("/projects")) {   //http://localhost:8081/users/projects
            projectsIndex(request, response);
        }

        //Проекты конкретного пользователя
        else if (path.matches("/\\d+/projects")) {
            showPersonalProjects(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }


    }
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
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

    @SneakyThrows
    private void index(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("users", userService.getAll());
        request.getRequestDispatcher("/WEB-INF/users/index.jsp").forward(request, response);
    }
    @SneakyThrows
    private void showUserProfile(HttpServletRequest request, HttpServletResponse response){

        request.setAttribute("user", userService.getById(extractId(request)));
        request.getRequestDispatcher("/WEB-INF/users/show.jsp").forward(request, response);
    }
    @SneakyThrows
    private void editUserProfile(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("user", userService.getById(extractId(request)));
        request.getRequestDispatcher("/WEB-INF/users/edit.jsp").forward(request, response);


    }

    @SneakyThrows
    private void create(HttpServletRequest request, HttpServletResponse response) {
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
        ((UserService) userService).create(info); //

        response.sendRedirect(request.getContextPath() + "/users");
    }

    @SneakyThrows
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        userService.deleteById(extractId(request));
        response.sendRedirect(request.getContextPath() + "/users");
    }


    @SneakyThrows
    private void update(HttpServletRequest request, HttpServletResponse response) {

        Long id = extractId(request);
        UserInfo info = new UserInfo(request.getParameter("userName"),
                request.getParameter("password"),request.getParameter("email"));

        ((UserService) userService).edit(id, info);

        response.sendRedirect(request.getContextPath() + "/users");
    }



    @SneakyThrows
    private void projectsIndex(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("projects", projectService.getAll());
        request.getRequestDispatcher("/WEB-INF/projects/index.jsp").forward(request,response);

    }
    @SneakyThrows
    private void showPersonalProjects(HttpServletRequest request, HttpServletResponse response) {

        Long userId = extractId(request);

        List<Project> projects = projectService.getByUserId(userId,projectDao);

        request.setAttribute("projects", projects);
        request.getRequestDispatcher("/WEB-INF/projects/show.jsp").forward(request, response);

    }
    @SneakyThrows
    private void editProjects(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("project", projectService.getById(extractId(request)));
        request.getRequestDispatcher("/WEB-INF/projects/edit.jsp").forward(request, response);
    }













    //Комментарии

    private void commentsIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("comments",userService.getAll());
        request.getRequestDispatcher("/WEB-INF/comments/index.jsp").forward(request, response);


    }

    //Список заданий



}




