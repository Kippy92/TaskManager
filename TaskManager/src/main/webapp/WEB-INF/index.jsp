<!doctype html>
<html lang="en">
<head>
	<title>Index</title>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<h1>Welcome ${user.firstname}</h1>
		
		<a href="/logout">Logout</a>
		<h2>Current Tasks</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Task</th>
					<th>Notes</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Complete?</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tasks}" var="task">
					<tr>
						<td><c:out value="${task.title}"/></td>
						<td><a href="/tasks/${task.id}">Notes</a></td>
						<td><fmt:formatDate value="${task.start_date}" pattern="MM-dd-yyyy" /></td>
						<td><fmt:formatDate value="${task.end_date}" pattern="MM-dd-yyyy" /></td>
						<td>
							<form:form action="/tasks/${task.id}" method="post" modelAttribute="show">
							    <input type="hidden" name="_method" value="put">
							    <input type="hidden" id="completed" name="completed" value="1">
							    <input type="submit" value="Complete"/>
							</form:form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h2>Completed Tasks</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Task</th>
					<th>Notes</th>
					<th>Start Date</th>
					<th>End Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${completed}" var="task">
					<tr>
						<td><c:out value="${task.title}"/></td>
						<td><a href="/tasks/${task.id}">Notes</a></td>
						<td><fmt:formatDate value="${task.start_date}" pattern="MM-dd-yyyy" /></td>
						<td><fmt:formatDate value="${task.end_date}" pattern="MM-dd-yyyy" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/tasks/addTask">Add a task</a>
	</div>    
</body>
</html>