<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Projects</title>
</head>
<body>
<h2>Project Info</h2>


<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Actions</th>
    </tr>

    <c:forEach var = "project" items = "${projects}">

        <tr>
            <td>${project.id}</td>
            <td>${project.projectName}</td>

            <td>
                <a href="${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}">View</a> |
                <a href="${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}/edit">Edit</a> |
                <form action="${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}/delete" method="post" style="display:inline;">
                    <button type="submit" onclick="return confirm('Are you sure?')">Delete</button>
                </form>
            </td>
        </tr>

    </c:forEach>

</table>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/projects/new">Create New Project</a>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/users">Back to List</a>
</body>
</html>
