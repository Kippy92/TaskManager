<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8" />
	    <title>Task</title>
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
	    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
	</head>
	<body> 
	<a href="/main">Dashboard</a>   
		<h1><c:out value="${show.title}"/></h1>
		<h2><c:out value="${show.network}"/></h2>
		<div class="container">
			<table class="table table-bordered">
					<h1>Task: ${task.title}</h1>
					<thead>
						<tr>
							<th>Date</th>
							<th>Note</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${n}" var="note">
							<tr>
								<td><fmt:formatDate value="${note.created_at}" pattern="MM-dd-yyyy" /></td>
								<td>${note.info}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<h2>Add a note:</h2>
			<form:form action="/notes" method="post" modelAttribute="note">
			    <p>
			        <form:label path="info">Note: </form:label>
			        <form:errors path="info" style="color: red;" />
			        <form:textarea path="info" rows="3" cols="50" />
			    </p>
			    <input type="hidden" id="task_id" name="task_id" value="${task.id}">
			    <input type="submit" value="Submit"/>
			</form:form>
		</div>  
	</body>
</html>    
