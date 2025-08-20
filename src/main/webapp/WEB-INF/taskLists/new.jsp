<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create TaskList</title>
</head>
<body>
<h2>Create New TaskList</h2>

<form action="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/createTaskList" method="post">
    <label>Title:</label><br>
    <input type="text" name="title" required>
    <br>
    <br>
    <input type="submit" value="Create">
</form>

<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects">Cancel</a>
</body>
</html>
