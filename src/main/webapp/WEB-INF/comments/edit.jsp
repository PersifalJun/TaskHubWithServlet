<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Comment</title>
</head>
<body>
<h2>Edit Comment</h2>

<form action="${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/comments/${comment.id}/update" method="post">
    <label>Content:</label>
    <br>
    <input type="text" name="content" value="${comment.content}" required>
    <br>
    <br>
    <label>Date of creation:</label>
    <br>
    <input type="text" name="creationDate" value="${comment.creationDate}" required>
    <br>
    <br>
    <input type="submit" value="Update">


</form>
<br>
<br>
<a href ="${pageContext.request.contextPath}/users/${user.id}">Back to User</a>
<br>
<br>
<a href = "${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}/taskList/${taskList.id}">Back to taskList</a>
</body>
</html>
