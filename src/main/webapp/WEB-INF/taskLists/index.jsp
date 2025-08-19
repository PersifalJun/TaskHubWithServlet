<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TaskLists</title>
</head>
<body>
<h2>All TaskLists</h2>


<br><br>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Project</th>
    </tr>

    <c:forEach var = "taskLists" items = "${taskLists}">

        <tr>
            <td>${taskLists.id}</td>
            <td>${taskLists.title}</td>
            <td>${taskLists.project}</td>

        </tr>

    </c:forEach>

</table>


</body>
</html>
