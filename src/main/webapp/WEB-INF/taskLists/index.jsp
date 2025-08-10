<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>All Users</h2>


<br><br>

<table border="1" cellpadding="5">
    <tr>
    <th>ID</th>
    <th>Content</th>
    <th>Date of creation</th>
    <th>User</th>
        <th>Task</th>
    </tr>

    <c:forEach var = "comment" items = "${comments}">

    <tr>
        <td>${comment.id}</td>
        <td>${comment.content}</td>
        <td>${comment.creationDate}</td>
        <td>${comment.author}</td>
        <td>${comment.task}</td>
        <td>
            <a href="${pageContext.request.contextPath}/comments/${comment.id}">View</a> |
            <a href="${pageContext.request.contextPath}/comments/${comment.id}/edit">Edit</a> |
            <form action="${pageContext.request.contextPath}/comments/${comment.id}/delete" method="post" style="display:inline;">
                <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
            </form>
        </td>
    </tr>

    </c:forEach>

</table>
<br/>
<a href="${pageContext.request.contextPath}/comments/new">Create New Comment</a>

</body>
</html>
