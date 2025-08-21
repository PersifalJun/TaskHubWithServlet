<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Task</title>
</head>
<body>
<h2>Create New Task</h2>

<form action="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/createTask" method="post">

    <label>Title of task:</label><br>
    <input type="text" name="title" required>
    <br>
    <br>
    <label>Description:</label><br>
    <input type="text" name="description" required>
    <br>
    <br>
    <label>Completed:</label><br>
    <input type="text" name="completed" required>
    <br>
    <br>
    <label>Date of Creation:</label><br>
    <input type="text" name="creationDate" required>
    <br>
    <br>
    <label>Deadline:</label><br>
    <input type="text" name="deadline" required>
    <br>
    <br>
    <input type="submit" value="Create">
</form>

<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}">Cancel</a>
</body>
</html>
