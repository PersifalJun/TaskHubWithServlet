package com.taskhub.controller;

import com.taskhub.config.PropertiesSessionFactoryProvider;
import com.taskhub.config.SessionFactoryProvider;
import com.taskhub.dao.DAO;
import com.taskhub.dao.ProjectDAO;
import com.taskhub.dao.UserDAO;
import com.taskhub.dto.ProjectInfo;
import com.taskhub.dto.UserInfo;
import com.taskhub.entity.Project;
import com.taskhub.entity.User;
import com.taskhub.service.ProjectService;
import com.taskhub.service.Service;
import com.taskhub.service.UserService;
import com.taskhub.utils.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;



import java.io.IOException;

import java.util.List;


import static java.util.Objects.isNull;

@WebServlet(name = "UserServlet", value = "/users*")
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
        //---------------------------------------//
        //Projects
        //Все проекты
        else if (path.equals("/projects")) {   //http://localhost:8081/users/projects
            projectsIndex(request, response);
        }
        //Проекты конкретного пользователя
        else if (path.matches("/\\d+/projects")) {
            showPersonalProjects(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/edit")) {
            editProjects(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }


    }
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String path =  request.getPathInfo();

        //Users
        if (path.equals("/create")) {
            create( request, response);
        } else if (path.matches("/\\d+/update")) {
            update(request, response);
        } else if (path.matches("/\\d+/delete")) {
            delete( request, response);

        }


        //---------------------------------------//
        //Projects
        else if (path.matches("/\\d+/projects/\\d+/update")) {
           updateProjects(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/delete")) {
            deleteProjects(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }







    //---------------------------------------//
    //Users
    @SneakyThrows
    private void index(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("users", userService.getAll());
        request.getRequestDispatcher("/WEB-INF/users/index.jsp").forward(request, response);
    }
    @SneakyThrows
    private void showUserProfile(HttpServletRequest request, HttpServletResponse response){

        request.setAttribute("user", userService.getById(Util.extractUserId(request)));
        request.getRequestDispatcher("/WEB-INF/users/show.jsp").forward(request, response);
    }
    @SneakyThrows
    private void editUserProfile(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("user", userService.getById(Util.extractUserId(request)));
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
        userService.deleteById(Util.extractUserId(request));
        response.sendRedirect(request.getContextPath() + "/users");
    }


    @SneakyThrows
    private void update(HttpServletRequest request, HttpServletResponse response) {

        Long id = Util.extractUserId(request);
        UserInfo info = new UserInfo(request.getParameter("userName"),
                request.getParameter("password"),request.getParameter("email"));

        ((UserService) userService).edit(id, info);

        response.sendRedirect(request.getContextPath() + "/users");
    }



    //---------------------------------------//
    //Projects
    @SneakyThrows
    private void projectsIndex(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("projects", projectService.getAll());
        request.getRequestDispatcher("/WEB-INF/projects/index.jsp").forward(request,response);

    }
    @SneakyThrows
    private void showPersonalProjects(HttpServletRequest request, HttpServletResponse response) {
        Long userId = Util.extractUserId(request);


        List<Project> projects = projectService.getByUserId(userId, projectDao);


        request.setAttribute("projects", projects);
        request.setAttribute("owner", userService.getById(userId));

        request.getRequestDispatcher("/WEB-INF/projects/show.jsp").forward(request, response);
    }
    @SneakyThrows
    private void editProjects(HttpServletRequest request, HttpServletResponse response) {
        Long userId = Util.extractUserId(request);
        Long projectId = Util.extractProjectId(request);

        Project project = projectService.getById(projectId);

        if (project == null || project.getOwner() == null || !project.getOwner().getId().equals(userId)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found for this user");
            return;
        }

        request.setAttribute("project", project);
        request.setAttribute("user", project.getOwner());
        request.getRequestDispatcher("/WEB-INF/projects/edit.jsp").forward(request, response);
    }

    @SneakyThrows
    private void updateProjects(HttpServletRequest request, HttpServletResponse response) {
        Long id = Util.extractUserId(request);
        Long projectId = Util.extractProjectId(request);
        ProjectInfo info = new ProjectInfo(request.getParameter("projectName"));

        ((ProjectService) projectService).edit(projectId, info);

        response.sendRedirect(request.getContextPath() + "/users/"+id+"/projects");
    }

    @SneakyThrows
    private void createProject(HttpServletRequest request, HttpServletResponse response) {
        String projectName = request.getParameter("projectName");
        if(isNull(projectName) || projectName.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
        }
        ProjectInfo projectInfo =  new ProjectInfo(projectName);
        ((ProjectService) projectService).create(projectInfo);

        response.sendRedirect(request.getContextPath() + "/projects");
    }

    @SneakyThrows
    private void deleteProjects(HttpServletRequest request, HttpServletResponse response) {
        Long ownerId = Util.extractUserId(request);
        Long projectId = Util.extractProjectId(request);

        Project project = projectService.getById(projectId);
        if (isNull(project) || !project.getOwner().getId().equals(ownerId)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found or you don't have permission");
            return;
        }

        projectService.deleteById(projectId);


        response.sendRedirect(request.getContextPath() + "/users/" + ownerId + "/projects");
    }














    //Комментарии

    private void commentsIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("comments",userService.getAll());
        request.getRequestDispatcher("/WEB-INF/comments/index.jsp").forward(request, response);


    }

    //Список заданий



}




