<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Comment</title>
</head>
<body>
<h2>Create New Comment</h2>

<form action="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/comments/createComment" method="post">
    <label>Content:</label><br>
    <input type="text" name="content" required>
    <br>
    <br>
    <label>Date of Creation:</label><br>
    <input type="text" name="creationDate" required>
    <br>
    <br>
    <input type="submit" value="Create">
</form>

<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}">Cancel</a>
</body>
</html>
