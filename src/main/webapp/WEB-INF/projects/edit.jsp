<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Project</title>
</head>
<body>
<h2>Edit Project</h2>

<form action="${pageContext.request.contextPath}/users/${project.owner.id}/projects/${project.id}/update" method="post">
    <label>ProjectName:</label>
    <br>
    <input type="text" name="projectName" value="${project.projectName}" required>
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
