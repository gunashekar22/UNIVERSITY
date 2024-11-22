<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Faculty Login</title>
<link type="text/css" rel="stylesheet" href="css/login-page.css">
</head>
<body>
 <div class="login-container">
        <h2>Faculty Login</h2>
        
        <form action="FacultyControllerServlet" method="GET">
        <input type="hidden" name="command" value="LOGIN" />
            
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" required oncopy="preventCopy(event)">
            </div>
            <button type="submit" class="login-btn">Login</button>
        </form>
        <a href="login-page.jsp">Student Login</a>
    </div>
    
    
<script>

function preventCopy(event){
	event.preventDefault();
} 


</script>
</body>

</html>