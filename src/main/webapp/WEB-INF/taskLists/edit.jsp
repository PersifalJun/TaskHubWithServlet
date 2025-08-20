<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit TaskList</title>
</head>
<body>
<h2>Edit TaskList</h2>

<form action="${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}/tasklist/${taskList.id}/update" method="post">
    <label>Title:</label>
    <br>
    <input type="text" name="title" value="${taskList.title}" required>
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
