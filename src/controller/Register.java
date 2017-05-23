package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.UserDAO;
import dao.UserDAOImpl;
import entity.User;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	static Logger logger = Logger.getLogger(EditUser.class);
	
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ServletContext context = getServletContext();  
	      Connection con = (Connection) context.getAttribute("DBConnection");
	      
	      String fullname = request.getParameter("fullName");
	      String gender = request.getParameter("gender");
	      String state = request.getParameter("state");
	      String city = request.getParameter("city");
	      String zipcode = request.getParameter("zipcode");
	      String birthyear = request.getParameter("birthYear");
	      String email = request.getParameter("loginEmail");
	      String pass = request.getParameter("loginPass");
	      
      	  User user = new User();
	      
	      user.setFullname(fullname);
	      user.setCity(city);
	      user.setEmail(email);
	      user.setGender(gender);
	      user.setState(state);
	      user.setZipcode(zipcode);
	      user.setBirthyear(birthyear);
	      user.setPassword(pass);
	      
	      dao = new UserDAOImpl(con);	
	      
	      User userCheck = dao.getUserByEmail(email);
	      
	      if(userCheck != null)
	      {
	    	  RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
	  		  PrintWriter out= response.getWriter();
	  		  
	    	  logger.error("User with this email address already registered" + email);		
				
			  out.println("<script>alert('User with this email address already registered...');</script>");
			  rd.include(request, response);
	      }
	      else
	      {
		      dao.addUser(user);
		      
		      logger.info("User added" + user.toString());	
		      
			  HttpSession session = request.getSession();
			  session.setAttribute("User", user);
				
			  response.sendRedirect("index.jsp");
	      }
	}

}
