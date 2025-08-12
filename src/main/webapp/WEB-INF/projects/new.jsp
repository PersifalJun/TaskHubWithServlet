<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Project</title>
</head>
<body>
<h2>Create New Project</h2>

<form action="${pageContext.request.contextPath}/users/${owner.id}/projects/create" method="post">
    <label>ProjectName:</label><br>
    <input type="text" name="projectName" required>
    <br>
    <br>
    <input type="submit" value="Create">
</form>

<br>
<a href="${pageContext.request.contextPath}/users/${owner.id}/projects">Cancel</a>
</body>
</html>
