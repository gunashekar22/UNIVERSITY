<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Deatils</title>
<link type="text/css" rel="stylesheet" href="css/display.css">
</head>
<body>
<div class="container">
	<h3>Student FirstName:<span>${THE_STUDENT.first_name}</span></h3>
	<h3>Student LastName:<span>${THE_STUDENT.last_name}</span></h3>
	<h3>Email:<span class="email">:${THE_STUDENT.email}</span></h3>
</div>
</body>
</html>