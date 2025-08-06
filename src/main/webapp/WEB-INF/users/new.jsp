<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h2>Create New User</h2>

<form action="${pageContext.request.contextPath}/users/create" method="post">
    <label>Username:</label><br>
    <input type="text" name="userName" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" required><br><br>

    <input type="submit" value="Create">
</form>

<br>
<a href="${pageContext.request.contextPath}/users">Cancel</a>
</body>
</html>
