<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h2>All Users</h2>


<br><br>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>${user.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${user.id}">View</a> |
                <a href="${pageContext.request.contextPath}/users/${user.id}/edit">Edit</a> |
                <a href ="${pageContext.request.contextPath}/users/${user.id}/projects">Projects</a> |
                <form action="${pageContext.request.contextPath}/users/${user.id}/delete" method="post" style="display:inline;">
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="${pageContext.request.contextPath}/users/new">Create New User</a>

<br>
<br>
<a href = ${pageConext.requst.pathContext}/users/projects>All projects</a>
<br>
<br>
<a href = ${pageConext.requst.pathContext}/users/tasks>All tasks</a>
<br>
<br>
<a href = ${pageConext.requst.pathContext}/users/taskLists>All taskLists</a>
<br>
<br>
<a href = ${pageConext.requst.pathContext}/users/comments>All comments</a>

</body>
</html>
