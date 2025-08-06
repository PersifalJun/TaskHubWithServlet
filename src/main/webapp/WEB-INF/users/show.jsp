<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Details</title>
</head>
<body>
<h2>User Info</h2>

<p><strong>ID:</strong> ${user.id}</p>
<p><strong>Username:</strong> ${user.userName}</p>
<p><strong>Email:</strong> ${user.email}</p>

<a href="${pageContext.request.contextPath}/users">Back to List</a>
</body>
</html>
