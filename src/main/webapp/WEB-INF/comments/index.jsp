<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Comments</title>
</head>
<body>
<h2>All Comments</h2>


<br><br>

<table border="1" cellpadding="5">
    <tr>
    <th>ID</th>
    <th>Content</th>
    <th>Date of creation</th>
    <th>Author</th>
        <th>Task</th>
    </tr>

    <c:forEach var = "comment" items = "${comments}">

    <tr>
        <td>${comment.id}</td>
        <td>${comment.content}</td>
        <td>${comment.creationDate}</td>
        <td>${comment.author.id}</td>
        <td>${comment.task.id}</td>
    </tr>

    </c:forEach>

</table>
<br>
<br>
<a href="${pageContext.request.contextPath}/users">Back to List</a>


</body>
</html>
