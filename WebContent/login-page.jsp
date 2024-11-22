<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Login</title>
<link type="text/css" rel="stylesheet" href="css/login-page.css">
</head>
<body>
 <div class="login-container">
        <h2>Student Login</h2>
        
        <form name="loginForm" action="StudentControllerServlet" method="GET" onsubmit="return validateForm()">
        <input type="hidden" name="command" value="LOGIN" />
            
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="text" id="password" name="password" required>
            </div>
            <button type="submit" class="login-btn">Login</button>
        </form>
        <a href="faculty-login.jsp">Faculty Login</a>
    </div>

    <!-- Prevent browser back button behavior -->
    <script type="text/javascript">
       
        function validateForm() {
            var email = document.forms["loginForm"]["email"].value;
            var password = document.forms["loginForm"]["password"].value;

            if (email == "" || password == "") {
                alert("Both email and password are required.");
                return false; 
            }
            return true; 
        }
    </script>
</body>
</html>
