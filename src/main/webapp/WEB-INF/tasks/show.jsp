<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Details</title>
</head>
<body>
<h2>Task Info</h2>

<p><strong>Title of task:</strong> ${task.title}</p>
<p><strong>Description:</strong> ${task.description}</p>
<p><strong>Completed:</strong> ${task.completed}</p>
<p><strong>Date of creation:</strong> ${task.creationDate}</p>
<p><strong>Deadline:</strong> ${task.deadline}</p>


<a href="${pageContext.request.contextPath}/users/${user.id}/projects/${project.id}/taskList/${taskList.id}">Back to Tasks</a>
</body>
</html>
