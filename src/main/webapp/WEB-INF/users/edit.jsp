<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<h2>Edit User</h2>

<form action="${pageContext.request.contextPath}/users/${user.id}/update" method="post">
    <label>Username:</label><br>
    <input type="text" name="userName" value="${user.userName}" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" value="${user.email}" required><br><br>

    <label>Password:</label><br>
    <input type="password" name="password" value="${user.password}" required><br><br>

    <input type="submit" value="Update">
</form>

<br>
<a href="${pageContext.request.contextPath}/users">Cancel</a>
</body>
</html>
