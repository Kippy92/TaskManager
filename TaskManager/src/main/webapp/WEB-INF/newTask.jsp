<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8" />
	    <title>Create a new Task</title>
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 		<link rel="stylesheet" href="/resources/demos/style.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script>
			$( function() {
			$( "#datepicker" ).datepicker();
			$( "#datepicker2" ).datepicker();
			} );
		</script>
	</head>
	<body>
		<div class="container">   
			<h1 style="text-align: center">New Task for ${user.firstname}</h1>
			<form:form action="/tasks" method="post" modelAttribute="task">
			    <p>
			        <form:label path="title">Title: </form:label>
			        <form:errors path="title" style="color: red;" />
			        <form:input path="title"/>
			    </p>
		        <p> 
		        	<form:label path="start_date">Start Date: </form:label>
			        <form:errors path="start_date" style="color: red;" />
			        <form:input id="datepicker" path="start_date" required="required" autocomplete="off" />
			    </p>
			    <p>
			        <form:label path="end_date">End Date: </form:label>
			        <form:input id="datepicker2" path="end_date" required="required" autocomplete="off" />
			        <form:errors path="end_date" style="color: red;" />
			    </p>
			    <input type="submit" value="create"/>
			</form:form>
		</div>    
	</body>
</html>