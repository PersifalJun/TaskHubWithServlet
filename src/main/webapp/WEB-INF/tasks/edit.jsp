<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Task</title>
</head>
<body>
<h2>Edit Task</h2>

<form action="${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/update" method="post">
    <label>Title of task:</label><br>
    <input type="text" name="title" value="${task.title}"required>
    <br>
    <br>
    <label>Description:</label><br>
    <input type="text" name="description" value="${task.description}" required>
    <br>
    <br>
    <label>Completed:</label><br>
    <input type="text" name="completed" value="${task.completed}" required>
    <br>
    <br>
    <label>Date of Creation:</label><br>
    <input type="text" name="creationDate" value="${task.creationDate}" required>
    <br>
    <br>
    <label>Deadline:</label><br>
    <input type="text" name="deadline" value="${task.deadline}" required>
    <br>
    <br>
    <input type="submit" value="Update">


</form>
<br>
<br>
<a href = "${pageContext.request.contextPath}/users/${user.id}">Back to User</a>
<br>
<br>
<a href = "${pageContext.request.contextPath}/users/">Back to Users</a>
<br>
<br>
<a href = "${pageContext.request.contextPath}/users/projects">Show all projects</a>
</body>
</html>
