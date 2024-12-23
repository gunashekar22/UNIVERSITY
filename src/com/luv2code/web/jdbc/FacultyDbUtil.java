package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;

public class FacultyDbUtil {

	private DataSource dataSource;

	public FacultyDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from web_student_tracker.student order by last_name";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phoneNumber=myRs.getString("phone_num");
				
				// create new student object
				Student tempStudent = new Student(id, firstName, lastName, email,phoneNumber);
				
				// add it to the list of students
				students.add(tempStudent);				
			}
			
			return students;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addStudent(Student theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			
			String sql = "insert into student "
					   + "(first_name, last_name, email,phone_num) "
					   + "values (?, ?, ?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			
			myStmt.setString(1, theStudent.getFirst_name());
			myStmt.setString(2, theStudent.getLast_name());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setString(4,"+91 "+theStudent.getPhoneNumber());
			
			
			myStmt.execute();
		}
		finally {
			
			close(myConn, myStmt, null);
		}
	}

	public Student getStudent(String theStudentId) throws Exception {

		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from student where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String phoneNumber=myRs.getString("phone_num");
				
				// use the studentId during construction
				theStudent = new Student(studentId, firstName, lastName, email,phoneNumber);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}				
			
			return theStudent;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

public void updateStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update student "
						+ "set first_name=?, last_name=?, email=?,phone_num=?"
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theStudent.getFirst_name());
			myStmt.setString(2, theStudent.getLast_name());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setString(4, theStudent.getPhoneNumber());
			myStmt.setInt(5, theStudent.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

public void deleteStudnet(String id) throws SQLException {
	// TODO Auto-generated method stub
	Connection myConn = null;
	PreparedStatement myStmt = null;
	int studentId=(Integer.parseInt(id));

	try {
		// get db connection
		myConn = dataSource.getConnection();
		
		// create SQL update statement
		String sql = "delete from student "+"where id=?";
		
		// prepare statement
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, studentId);
		
		
		myStmt.execute();
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, null);
	}
}

public boolean findFaculty(String email, String password) throws SQLException {
    Connection myConn = null;
    PreparedStatement myStmt = null;
    ResultSet myRs = null;

    try {
        
        myConn = dataSource.getConnection();

        
        String sql = "SELECT CONCAT(first_name, last_name) AS full_name FROM web_student_tracker.faculty "+"WHERE email =?";
        
        // Prepare statement
        myStmt = myConn.prepareStatement(sql);

        // Set parameter
        myStmt.setString(1, email);

        // Execute query
        myRs = myStmt.executeQuery();

        // Process result
        if (myRs.next()) {
            String passString=myRs.getString("full_name");
            if(passString.equals(password)) {
            	return true;
            }
           
        }
        
    } finally {
        // Close resources
        close(myConn, myStmt, myRs);
    }

  return false;
}

public Student getStudentByEmail(String emailString) throws Exception {
	Student theStudent = null;
	
	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
//	int studentId;
	
	try {
		// convert student id to int
//		studentId = Integer.parseInt(theStudentId);
		
		// get connection to database
		myConn = dataSource.getConnection();
		
		// create sql to get selected student
		String sql = "select * from student where email=?";
		
		// create prepared statement
		myStmt = myConn.prepareStatement(sql);
		
		// set params
		myStmt.setString(1, emailString);
		
		// execute statement
		myRs = myStmt.executeQuery();
		
		// retrieve data from result set row
		if (myRs.next()) {
//			String  studentId=myRs.getString("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			String phoneNumber=myRs.getString("phone_num");
			
			// use the studentId during construction
			theStudent = new Student(firstName, lastName, email,phoneNumber);
		}
		else {
			throw new Exception("Could not find student ");
		}				
		
		return theStudent;
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, myRs);
	}
}
}
///MaryPublic
//mary@luv2code.com

