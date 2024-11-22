
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<%@page import="com.luv2code.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css">

<title>Student Tracker App</title>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>Guna University</h2>
		</div>
		
	</div>
	<div id="container">
	<div id="content">
	<input type="button" value="Add Student"
	onclick="window.location.href='add-student-form.jsp';return false;"
	class="add-student-button"
	
	/>
	<table>
	<tr>
		<th>First Name </th>
		<th>Last Name </th>
		<th>Email</th>
		<th>PhoneNumber</th>
		<th>Action</th>
	
	</tr>
	<c:forEach var="stud"  items="${STUDENTS_LIST}">
	<c:url var="tempLink" value="StudentControllerServlet">
		<c:param name="command" value="LOAD"/>
		<c:param name="studentId" value="${stud.id}"/>
		
	</c:url>
	<c:url var="delLink" value="StudentControllerServlet">
		<c:param name="command" value="DELETE"/>
		<c:param name="studentId" value="${stud.id}"/>
		
	</c:url>
	<tr>
		<td>${stud.first_name}</td>
		<td>${stud.last_name}</td>
		<td>${stud.email}</td>
		<td>${stud.phoneNumber}</td>
		<td><a href="${tempLink}">Update</a>
				|
			<a href="${delLink}",
			onclick="if(!(confirm('Are you sure you want to delete ${stud.first_name}')))return false"
			>Delete</a>
		
		
		</td>
		
	</tr>
	</c:forEach>
	
	
	
	</table>
	
	</div>
	
	
	</div>




</body>
</html> 