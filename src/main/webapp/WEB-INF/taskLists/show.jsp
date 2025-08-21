<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project TaskList</title>
</head>
<body>
<h2>TaskList ${taskList.title} for Project ${project.id}</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Title of task</th>
        <th>Description</th>
        <th>Completed</th>
        <th>Date of creation</th>
        <th>Deadline</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="task" items="${tasksInTaskList}">
        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${task.completed}</td>
            <td>${task.creationDate}</td>
            <td>${task.deadline}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}">View</a> |
                <a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/edit">Edit</a> |
                <form action="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/delete" method="post" style="display:inline;">
                    <input type="hidden" name="_method" value="delete">
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects">Cancel</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/edit">Edit TaskList</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/newTask">Create Task</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}">Check User</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/users">Back to Users</a>
<br>
<br>
<form action="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/delete" method="post" style="display:inline;">
    <input type="hidden" name="_method" value="delete">
    <button type="submit" onclick="return confirm('Are you sure?')">Delete TaskList</button>
</form>
</body>
</html>


