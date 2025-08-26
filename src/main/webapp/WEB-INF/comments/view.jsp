<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Details</title>
</head>
<body>
<h2>Comment Info</h2>

<p><strong>ID:</strong> ${comment.id}</p>
<p><strong>Content:</strong> ${comment.content}</p>
<p><strong>Date of creation:</strong> ${comment.creationDate}</p>
<p><strong>Author:</strong> ${comment.author.id}</p>
<p><strong>Author:</strong> ${comment.task.id}</p>

<a href="${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/comments">Back to List</a>
</body>
</html>
