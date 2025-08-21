<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Projects</title>
</head>
<body>
<h2>All Projects</h2>


<br><br>

<table border="1" cellpadding="5">
    <tr>
    <th>ID</th>
    <th>Title</th>
    <th>User ID</th>
    </tr>

    <c:forEach var = "project" items = "${projects}">

    <tr>
        <td>${project.id}</td>
        <td>${project.projectName}</td>
        <td>${project.owner.id}</td>

    </tr>

    </c:forEach>

</table>
<br>
<br>
<a href="${pageContext.request.contextPath}/users">Cancel</a>

</body>
</html>
