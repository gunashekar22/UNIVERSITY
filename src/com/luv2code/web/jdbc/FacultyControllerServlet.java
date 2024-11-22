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

@WebServlet("/FacultyControllerServlet")
public class FacultyControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FacultyDbUtil facultyDbUtil;
    private StudentDbUtil studentDbUtil;
    private StudentControllerServlet studentControllerServlet;

    @Resource(name = "jdbc/web_student_tracker")
    private DataSource dataSource;
    


    @Override
    public void init() throws ServletException {
        super.init();
        try {
            if (dataSource == null) {
                throw new ServletException("DataSource injection failed");
            }
            facultyDbUtil = new FacultyDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
  
    	try {
        	String theCommand=request.getParameter("command");
        	switch (theCommand) {
			
			case "LOGIN":{
				if(verifyLogin(request,response)) {
//					
			        response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
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

   
 
//saahas@gmail.com   saahasundabatla

	private boolean verifyLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		boolean isFind=false;
		try {
			isFind=facultyDbUtil.findFaculty(email,password);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(!isFind) {
			
			response.sendRedirect(request.getContextPath());
			
		}
		return isFind;
		
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> students = studentDbUtil.getStudents();
        
        
        request.setAttribute("STUDENTS_LIST", students);
     
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
    }
}
