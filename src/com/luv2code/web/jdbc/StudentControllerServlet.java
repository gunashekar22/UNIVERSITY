package com.luv2code.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//import com.mysql.cj.jdbc.CallableStatementWrapper;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDbUtil studentDbUtil;

    @Resource(name = "jdbc/web_student_tracker")
    private DataSource dataSource;
    


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            if (dataSource == null) {
                throw new ServletException("DataSource injection failed");
            }
            studentDbUtil = new StudentDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
  
    	try {
        	String theCommand=request.getParameter("command");
        	if(theCommand==null) {
        		theCommand="LIST";
        	}
        	switch (theCommand) {
			case "LIST": {
				listStudents(request, response);
				break;
			}
			case "ADD":{
				addStudent(request,response);
				break;
			}
			case "LOAD":{
				loadStudent(request,response);
				break;
			}
			case "UPDATE":{
				updateStudent(request,response);
//				listStudents(request, response);
				break;
			}
			case "DELETE":{
				deleteStudnet(request,response);
				break;
			}
			case "LOGIN":{
				if(verifyLogin(request,response)) {
//					listStudents(request, response);
					displayStudent(request,response);
				}
	
				break;
			} 
	
			default:
				throw new IllegalArgumentException("Unexpected value: " + theCommand);
			}
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    private void displayStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    	request.setAttribute("name", "guna");
    	String emailString=request.getParameter("email");
//		request.setAttribute("name",request.getParameter("email"));
		Student theStudent=studentDbUtil.getStudentByEmail(emailString);
		request.setAttribute("THE_STUDENT", theStudent);
//		request.setAttribute("", response);
		
//		String theStudentId = request.getParameter("studentId");
//		
//		Student theStudent = studentDbUtil.getStudent(theStudentId);
//
//		request.setAttribute("THE_STUDENT", theStudent);
    	
		
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Student-detail.jsp");
    	dispatcher.forward(request, response);
	
	}
//saahas@gmail.com   saahasundabatla

	private boolean verifyLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		boolean isFind=false;
		try {
			isFind=studentDbUtil.findStudent(email,password);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(!isFind) {
			
			response.sendRedirect(request.getContextPath());
			
		}
		return isFind;
		
	}


	private void deleteStudnet(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String id=request.getParameter("studentId");
		studentDbUtil.deleteStudnet(id);
		response.sendRedirect("/web-student-tracker/StudentControllerServlet");
//		listStudents(request, response);
//		response.sendRedirect(request.getContentPath());
//		response.sendRedirect(request.getContextPath());
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
//        dispatcher.forward(request, response);
		
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
    		throws Exception {

    		
    		int id = Integer.parseInt(request.getParameter("studentId"));
    		String firstName = request.getParameter("firstName");
    		String lastName = request.getParameter("lastName");
    		String email = request.getParameter("email");
    		String phoneNumber = request.getParameter("phoneNumber");
    		
    		Student theStudent = new Student(id, firstName, lastName, email,phoneNumber);
    		
    		
    		studentDbUtil.updateStudent(theStudent);
    		
    		listStudents(request, response);
//    		response.sendRedirect(request.getContextPath());
//    		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
//            dispatcher.forward(request, response);
    		
    		
    	}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception {

    		
    		String theStudentId = request.getParameter("studentId");
    		
    		Student theStudent = studentDbUtil.getStudent(theStudentId);

    		request.setAttribute("THE_STUDENT", theStudent);
    		
    		RequestDispatcher dispatcher = 
    				request.getRequestDispatcher("/update-student-form.jsp");
    		dispatcher.forward(request, response);		
    	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");	
		String phoneNumber = request.getParameter("phoneNumber");
		
		// create a new student object
		Student theStudent = new Student(firstName, lastName, email,phoneNumber);
		
		studentDbUtil.addStudent(theStudent);
//		listStudents(request, response);
//		
		response.sendRedirect("/web-student-tracker/StudentControllerServlet");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
//        dispatcher.forward(request, response);
	}
	


	


	void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> students = studentDbUtil.getStudents();
        
        
        request.setAttribute("STUDENTS_LIST", students);
     
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }
}
