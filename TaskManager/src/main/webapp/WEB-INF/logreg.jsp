<!doctype html>
<html lang="en">
<head>
	<title>Login</title>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
	<div class="d-flex justify-content-center">
		<h1>Login/Register</h1>
	</div>
	<div class="d-flex justify-content-center">
		<div style="display: inline-block; width:250px;">
			<h2 style="padding-right: 20px;">Register</h2>
			<form action="/register" method="post">
				<p>First Name</p> <input type="text" name="firstname">
				<p style="color: red">${errors.firstname}</p>
				<p>Last Name</p> <input type="text" name="lastname">
				<p style="color: red">${errors.lastname}</p>
				<p>Email</p> <input type="text" name="email">
				<p style="color: red">${errors.email}</p>
				<p>Password</p><input type="password" name="password">
				<p style="color: red">${errors.password}</p>
				<p>Confirm</p> <input type="password" name="confirm">
				<p style="color: red">${errors.confirm}</p>
				<p><input type="submit" value="Register"></p>
			</form>
		</div>
		<div style="display: inline-block; vertical-align: top;">
			<h2 style="padding-right: 20px;">Login</h2>
			<form action="/login" method="post">
				<p>Email</p> <input type="text" name="email">
				<p style="color: red">${errors.email2}</p>
				<p>Password</p><input type="password" name="password">
				<p style="color: red">${errors.password2}</p>
				<p><input type="submit" value="Login"></p>
			</form>
		</div>
	</div>
</body>
</html>