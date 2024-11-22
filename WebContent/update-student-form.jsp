<!DOCTYPE html>
<html>

<head>
    <title>Add Student</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">

    <script type="text/javascript">
        function validateForm() {
            // Get all input fields
            var firstName = document.forms["studentForm"]["firstName"].value;
            var lastName = document.forms["studentForm"]["lastName"].value;
            var email = document.forms["studentForm"]["email"].value;
            var phoneNumber = document.forms["studentForm"]["phoneNumber"].value;

            // Check if any field is empty
            if (firstName == "" || lastName == "" || email == "" || phoneNumber == "") {
                alert("All fields must be filled out.");
                return false;  // Prevent form submission
            }
            return true;  // Allow form submission
        }
    </script>
</head>

<body>
    <div id="wrapper">
        <div id="header">
            <h2>Guna University</h2>
        </div>
    </div>
    
    <div id="container">
        <h3>Add Student</h3>
        
        <!-- Add onsubmit to call the validateForm function -->
        <form name="studentForm" action="StudentControllerServlet" method="GET" onsubmit="return validateForm()">
        
            <input type="hidden" name="command" value="UPDATE" />
            <input type="hidden" name="studentId" value="${THE_STUDENT.id}" />
            
            <table>
                <tbody>
                    <tr>
                        <td><label>First name:</label></td>
                        <td><input type="text" name="firstName" value="${THE_STUDENT.first_name}" /></td>
                    </tr>

                    <tr>
                        <td><label>Last name:</label></td>
                        <td><input type="text" name="lastName" value="${THE_STUDENT.last_name}" /></td>
                    </tr>

                    <tr>
                        <td><label>Email:</label></td>
                        <td><input type="text" name="email" value="${THE_STUDENT.email}" /></td>
                    </tr>
                    
                    <tr>
                        <td><label>Phone Num:</label></td>
                        <td><input type="text" name="phoneNumber" value="${THE_STUDENT.phoneNumber}" /></td>
                    </tr>
                    
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Update" class="save" /></td>
                    </tr>
                    
                </tbody>
            </table>
        </form>
        
        <p>
            <a href="StudentControllerServlet">Back to List</a>
        </p>
    </div>
</body>

</html>
