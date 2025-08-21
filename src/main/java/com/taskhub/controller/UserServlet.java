package com.taskhub.controller;

import com.taskhub.config.PropertiesSessionFactoryProvider;
import com.taskhub.config.SessionFactoryProvider;
import com.taskhub.dao.*;
import com.taskhub.dto.ProjectInfo;
import com.taskhub.dto.TaskInfo;
import com.taskhub.dto.TaskListInfo;
import com.taskhub.dto.UserInfo;
import com.taskhub.entity.Project;
import com.taskhub.entity.Task;
import com.taskhub.entity.TaskList;
import com.taskhub.entity.User;
import com.taskhub.service.*;
import com.taskhub.utils.Util;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;


import java.util.*;


import static java.util.Objects.isNull;

@WebServlet(name = "UserServlet", value = "/users*")
public class UserServlet extends HttpServlet {

    private final SessionFactoryProvider sessionFactoryProvider;
    private final DAO<User> userDAO;
    private final DAO<Project> projectDao;
    private final DAO<TaskList> taskListDao;
    private final DAO<Task> taskDao;
    private final Service<Task> taskService;
    private final Service<User> userService;
    private final Service<Project> projectService;
    private final Service<TaskList> taskListService;

    public UserServlet() {
        sessionFactoryProvider = new PropertiesSessionFactoryProvider();
        userDAO = new UserDAO(sessionFactoryProvider.getSessionFactory());
        projectDao = new ProjectDAO(sessionFactoryProvider.getSessionFactory());
        taskListDao = new TaskListDAO(sessionFactoryProvider.getSessionFactory());
        taskDao = new TaskDAO(sessionFactoryProvider.getSessionFactory());
        userService = new UserService(userDAO);
        projectService = new ProjectService(projectDao);
        taskListService = new TaskListService(taskListDao);
        taskService = new TaskService(taskDao);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        String path = request.getPathInfo(); // например, /1, /new, /edit






        //Users
        if (isNull(path) || path.equals("/")) {
            index(request, response);
        }
        else if (path.matches("/\\d+")) {
            showUserProfile(request, response);
        }
        else if (path.matches("/\\d+/edit")) {
            editUserProfile(request, response);
        }
        else if (path.equals("/new")) {
            request.getRequestDispatcher("/WEB-INF/users/new.jsp").forward(request, response);
        }








        //---------------------------------------//
        //Projects
        else if (path.equals("/projects")) {   //http://localhost:8081/users/projects -> all projects
            showAllProjects(request, response);
        }
        //Проекты конкретного пользователя
        else if (path.matches("/\\d+/projects")) {
            showPersonalProjects(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/edit")) {
            editProjects(request, response);
        }
        else if (path.matches("/\\d+/projects/new")) {
            showCreateProjectForm(request, response);
        }






        //---------------------------------------//
        //TaskLists

        else if (path.equals("/taskLists")) {   //http://localhost:8081/users/taskLists -> all taskLists
            showAllTaskLists(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/taskList/\\d+")) {
            showTasksInTaskListForProject(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/newTaskList")) {
            showCreateTaskListForm(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/taskList/\\d+/edit")) {
            editTaskList(request, response);
        }








        //---------------------------------------//
        //Tasks
        else if (path.equals("/tasks")) {   //http://localhost:8081/users/tasks -> all tasks
            showAllTasks(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/taskList/\\d+/task/\\d+")) {
            showTask(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/taskList/\\d+/newTask")) {   //http://localhost:8081/users/tasks -> all tasks
            showCreateTaskForm(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/taskList/\\d+/task/\\d+/edit")) {
            editTask(request, response);
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
        else if(path.matches("/\\d+/projects/create")){
            createProject(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/delete")) {
            deleteProjects(request, response);
        }





        //---------------------------------------//
        //TaskLists
        else if(path.matches("/\\d+/projects/\\d+/createTaskList")){
            createTaskList(request, response);
        }
        else if(path.matches("/\\d+/projects/\\d+/taskList/\\d+/update")){
           updateTaskList(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/taskList/\\d+/delete")) {
            deleteTaskList(request, response);
        }
        else if(path.matches("/\\d+/projects/\\d+/taskList/\\d+/createTask")){
            createTask(request, response);
        }





        //---------------------------------------//
        //Task
        else if(path.matches("/\\d+/projects/\\d+/taskList/\\d+/task/\\d+/update")){
            updateTask(request, response);
        }
        else if (path.matches("/\\d+/projects/\\d+/taskList/\\d+/task/\\d+/delete")) {
            deleteTask(request, response);
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
    private void showAllProjects(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("projects", projectService.getAll());
        request.getRequestDispatcher("/WEB-INF/projects/index.jsp").forward(request,response);

    }
    @SneakyThrows
    private void showPersonalProjects(HttpServletRequest request, HttpServletResponse response) {
        Long userId = Util.extractUserId(request);
        List<Project> projects = projectService.getByUserId(userId, projectDao);


        //Map for Project and TaskList
        Map<Long, Long> taskListIds = new HashMap<>();
        for (Project project : projects) {
            TaskList taskList = ((TaskListService) taskListService).getByProjectId(project.getId(), taskListDao);
            if (Objects.nonNull(taskList)) {
                taskListIds.put(project.getId(), taskList.getId());
            }
        }

        request.setAttribute("taskListIds", taskListIds);
        request.setAttribute("projects", projects);
        request.setAttribute("owner", userService.getById(userId));
        request.getRequestDispatcher("/WEB-INF/projects/show.jsp").forward(request, response);

    }
    @SneakyThrows
    private void editProjects(HttpServletRequest request, HttpServletResponse response) {
        Long userId = Util.extractUserId(request);
        Long projectId = Util.extractProjectId(request);
        Project project = projectService.getById(projectId);

        if (isNull(project) || isNull(project.getOwner()) || !project.getOwner().getId().equals(userId)) {
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
        Long ownerId = Util.extractUserId(request);
        User owner = userService.getById(ownerId);

        if(isNull(projectName) || projectName.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
        }
        ProjectInfo projectInfo =  new ProjectInfo(projectName);
        ((ProjectService) projectService).create(projectInfo,owner);


        response.sendRedirect(request.getContextPath() + "/users/" + ownerId + "/projects");

    }

    @SneakyThrows
    private void showCreateProjectForm(HttpServletRequest request, HttpServletResponse response) {
        Long ownerId = Util.extractUserId(request);
        User owner = userService.getById(ownerId);

        if (isNull(owner)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        request.setAttribute("owner", owner);
        request.getRequestDispatcher("/WEB-INF/projects/new.jsp").forward(request, response);
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












    //-----------------
    //TaskList
    @SneakyThrows
    private void showAllTaskLists(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("taskLists", taskListService.getAll());
        request.getRequestDispatcher("/WEB-INF/taskLists/index.jsp").forward(request, response);

    }

    @SneakyThrows
    private void showTasksInTaskListForProject(HttpServletRequest request, HttpServletResponse response) {

        Long projectId = Util.extractProjectId(request);
        Long taskListId = Util.extractTaskListId(request);


        Project project = projectService.getById(projectId);

        TaskList taskList = taskListService.getById(taskListId);
        TaskList taskListForProject = ((TaskListService) taskListService).getByProjectId(projectId,taskListDao);
        List<Task> tasksInTaskList = ((TaskService) taskService).getByTaskListId(taskListForProject.getId(),taskDao);


        request.setAttribute("project", project);
        request.setAttribute("taskList", taskList);
        request.setAttribute("tasksInTaskList", tasksInTaskList);
        request.setAttribute("owner", project.getOwner());
        request.getRequestDispatcher("/WEB-INF/taskLists/show.jsp").forward(request, response);

    }
    @SneakyThrows
    private void createTaskList(HttpServletRequest request, HttpServletResponse response) {

        String title = request.getParameter("title");
        Long projectId = Util.extractProjectId(request);
        Long ownerId = Util.extractUserId(request);
        Project project = projectService.getById(projectId);

        if(isNull(title) || title.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
        }
        TaskListInfo taskListInfo =  new TaskListInfo(title);
        ((TaskListService) taskListService).create(taskListInfo,project);


        response.sendRedirect(request.getContextPath() + "/users/" + ownerId + "/projects");
    }


    @SneakyThrows
    private void showCreateTaskListForm(HttpServletRequest request, HttpServletResponse response) {
        Long ownerId = Util.extractUserId(request);
        User owner = userService.getById(ownerId);
        Long projectId = Util.extractProjectId(request);
        Project project = projectService.getById(projectId);

        if (isNull(owner)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        request.setAttribute("project", project);
        request.setAttribute("owner", owner);
        request.getRequestDispatcher("/WEB-INF/taskLists/new.jsp").forward(request, response);
    }


    @SneakyThrows
    private void updateTaskList(HttpServletRequest request, HttpServletResponse response) {
        Long id = Util.extractUserId(request);
        Long taskListId = Util.extractTaskListId(request);
        Long projectId = Util.extractProjectId(request);


        TaskListInfo taskListInfo = new TaskListInfo(request.getParameter("title"));
        ((TaskListService) taskListService).edit(taskListId, taskListInfo);

        response.sendRedirect(request.getContextPath() + "/users/"+id+"/projects/"+projectId+"/taskList/"+taskListId);
    }

    @SneakyThrows
    private void editTaskList(HttpServletRequest request, HttpServletResponse response) {


        Long projectId = Util.extractProjectId(request);
        Long taskListId = Util.extractTaskListId(request);
        TaskList taskList = taskListService.getById(taskListId);
        Project project = projectService.getById(projectId);

        if (isNull(taskList)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found for this user");
            return;
        }
        request.setAttribute("taskList", taskList);
        request.setAttribute("project", project);
        request.setAttribute("user", project.getOwner());
        request.getRequestDispatcher("/WEB-INF/taskLists/edit.jsp").forward(request, response);

    }

    @SneakyThrows
    private void deleteTaskList(HttpServletRequest request, HttpServletResponse response) {
        Long ownerId = Util.extractUserId(request);
        Long taskListId = Util.extractTaskListId(request);

        TaskList taskList = taskListService.getById(taskListId);
        if (isNull(taskList)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "TaskList not found or you don't have permission");
            return;
        }
        Project project = taskList.getProject();
        if(Objects.nonNull(project)) {
            project.setTaskList(null);
            projectService.update(project);
        }
        taskListService.deleteById(taskListId);

        response.sendRedirect(request.getContextPath() + "/users/" + ownerId + "/projects");
    }










    //----------------------------
    //Tasks
    @SneakyThrows
    private void showAllTasks(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("tasks", taskService.getAll());
        request.getRequestDispatcher("/WEB-INF/tasks/index.jsp").forward(request, response);

    }
    @SneakyThrows
    private void showTask(HttpServletRequest request, HttpServletResponse response) {
        Long taskId = Util.extractTaskId(request);
        Long projectId = Util.extractProjectId(request);
        Long userId = Util.extractUserId(request);
        Long taskListId = Util.extractTaskListId(request);

        request.setAttribute("task", taskService.getById(taskId));
        request.setAttribute("project", projectService.getById(projectId));
        request.setAttribute("user",userService.getById(userId));
        request.setAttribute("taskList", taskListService.getById(taskListId));
        request.getRequestDispatcher("/WEB-INF/tasks/show.jsp").forward(request, response);

    }

    @SneakyThrows
    private void createTask(HttpServletRequest request, HttpServletResponse response) {

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String completed = request.getParameter("completed");
        String creationDate = request.getParameter("creationDate");
        String deadline = request.getParameter("deadline");
        Long projectId = Util.extractProjectId(request);
        Long ownerId = Util.extractUserId(request);
        Long taskListId = Util.extractTaskListId(request);
        TaskList taskList = taskListService.getById(taskListId);

        if(isNull(title) || title.isBlank() || isNull(description) || description.isBlank()||
                isNull(completed) || completed.isBlank() || isNull(creationDate) || creationDate.isBlank()||
                isNull(deadline) || deadline.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
        }
        TaskInfo taskInfo =  new TaskInfo(title,description,completed,creationDate,deadline);
        ((TaskService) taskService).create(taskInfo,taskList);


        response.sendRedirect(request.getContextPath() + "/users/" + ownerId + "/projects/"+projectId+"/taskList/"+taskListId);

    }

    @SneakyThrows
    private void showCreateTaskForm(HttpServletRequest request, HttpServletResponse response) {
        Long ownerId = Util.extractUserId(request);
        User owner = userService.getById(ownerId);
        Long taskListId = Util.extractTaskListId(request);
        TaskList taskList = taskListService.getById(taskListId);
        Long projectId = Util.extractProjectId(request);
        Project project = projectService.getById(projectId);

        if (isNull(owner)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        request.setAttribute("taskList", taskList);
        request.setAttribute("project", project);
        request.setAttribute("owner", owner);
        request.getRequestDispatcher("/WEB-INF/tasks/new.jsp").forward(request, response);

    }
    @SneakyThrows
    private void editTask(HttpServletRequest request, HttpServletResponse response) {

        Long projectId = Util.extractProjectId(request);
        Long taskListId = Util.extractTaskListId(request);
        Long taskId= Util.extractTaskId(request);
        TaskList taskList = taskListService.getById(taskListId);
        Project project = projectService.getById(projectId);
        Task task = taskService.getById(taskId);

        if (isNull(taskList)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found for this user");
            return;
        }
        request.setAttribute("task", task);
        request.setAttribute("taskList", taskList);
        request.setAttribute("project", project);
        request.setAttribute("user", project.getOwner());
        request.getRequestDispatcher("/WEB-INF/tasks/edit.jsp").forward(request, response);


    }
    @SneakyThrows
    private void updateTask(HttpServletRequest request, HttpServletResponse response) {
        Long id = Util.extractUserId(request);
        Long taskListId = Util.extractTaskListId(request);
        Long projectId = Util.extractProjectId(request);
        Long taskId = Util.extractTaskId(request);

        TaskInfo taskInfo = new TaskInfo(request.getParameter("title"),request.getParameter("description"),
                request.getParameter("completed"),request.getParameter("creationDate"),request.getParameter("deadline"));
        ((TaskService) taskService).edit(taskId, taskInfo);

        response.sendRedirect(request.getContextPath() + "/users/"+id+"/projects/"+projectId+"/taskList/"+taskListId);

    }


    @SneakyThrows
    private void deleteTask(HttpServletRequest request, HttpServletResponse response) {

        Long ownerId = Util.extractUserId(request);
        Long taskListId = Util.extractTaskListId(request);
        Long projectId = Util.extractProjectId(request);
        Long taskId = Util.extractTaskId(request);
        Task task = taskService.getById(taskId);

        if (isNull(task)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "TaskList not found or you don't have permission");
            return;
        }

        TaskList taskList = task.getTaskList();
        if (taskList != null) {
            taskList.getTasks().remove(task);
            taskListService.update(taskList);
        }

        taskService.deleteById(taskId);

        response.sendRedirect(request.getContextPath() + "/users/" + ownerId + "/projects/" + projectId + "/taskList/"+taskListId);

    }















    //--------------------------------------
    //Comments
    @SneakyThrows
    private void showAllCommentsForTask(HttpServletRequest request, HttpServletResponse response)  {

    }

    @SneakyThrows
    private void createComments(HttpServletRequest request, HttpServletResponse response) {

    }

    @SneakyThrows
    private void deleteComments(HttpServletRequest request, HttpServletResponse response) {

    }
    @SneakyThrows
    private void updateComments(HttpServletRequest request, HttpServletResponse response) {

    }
    @SneakyThrows
    private void editComments(HttpServletRequest request, HttpServletResponse response) {

    }






}




