<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tasks</title>
</head>
<body>
<h2>All Tasks</h2>


<br><br>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Completed</th>
        <th>Date of creation</th>
        <th>Deadline</th>
        <th>TaskList ID</th>




    </tr>

    <c:forEach var = "task" items = "${tasks}">

        <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${task.completed}</td>
            <td>${task.creationDate}</td>
            <td>${task.deadline}</td>
            <td>${task.taskList}</td>



        </tr>

    </c:forEach>

</table>


</body>
</html>
