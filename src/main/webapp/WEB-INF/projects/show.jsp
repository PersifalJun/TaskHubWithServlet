<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Projects</title>
</head>
<body>
<h2>Projects for User ${owner.id} (${owner.userName})</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.id}</td>
            <td>${project.projectName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/edit">Edit</a> |
                <c:if test="${taskListIds[project.id] != null}">
                    <a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/taskList/${taskListIds[project.id]}">TaskList</a> |
                </c:if>
                <c:if test="${taskListIds[project.id] == null}">
                    <a href="${pageContext.request.contextPath}/users/${owner.id}/projects/${project.id}/newTaskList">Create TaskList</a> |
                </c:if>
                <form action="${pageContext.request.contextPath}/users/${project.owner.id}/projects/${project.id}/delete" method="post" style="display:inline;">
                    <input type="hidden" name="_method" value="delete">
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects/new">Create New Project</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}">Check User</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/users/projects">All projects</a>
</body>
</html>
