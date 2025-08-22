<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comments for Task</title>
</head>
<body>
<h2>Comments for Task ${task.title} </h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Content</th>
        <th>Date of creation</th>
        <th>Author</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="comment" items="${commentsForTask}">
        <tr>
            <td>${comment.id}</td>
            <td>${comment.content}</td>
            <td>${comment.creationDate}</td>
            <td>${comment.author.id}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/comments/${comment.id}">View</a> |
                <a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/comments/${comment.id}/edit">Edit</a> |
                <form action="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/comments/${comment.id}/delete" method="post" style="display:inline;">
                    <input type="hidden" name="_method" value="delete">
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}/task/${task.id}/comments/newComment">Create Comment</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskList.id}">Cancel</a>

<br>
<br>
<a href="${pageContext.request.contextPath}/users">Back to Users</a>
<br>
<br>
</body>
</html>


