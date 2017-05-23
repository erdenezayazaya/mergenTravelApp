package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;

import org.apache.log4j.Logger;

import dao.UserDAO;
import dao.UserDAOImpl;
import entity.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	
	static Logger logger = Logger.getLogger(Login.class);

    public Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ServletContext context = getServletContext();  
        Connection con = (Connection) context.getAttribute("DBConnection");
        
        dao = new UserDAOImpl(con);
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = dao.getUserByEmail(email);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
		PrintWriter out= response.getWriter();
		
		if(user == null)
		{
			logger.error("User not found with email=" + email);		
			
			out.println("<script>alert('There is no user info found according to this email...');</script>");
			rd.include(request, response);
		}
		else
		{
			logger.info("User found with details =" + user);
			
			if(!user.getPassword().equals(password))
			{
				logger.error("Password is wrong =" + user);
				
				out.println("<script>alert('Password is wrong ...');</script>");
				rd.include(request, response);
			}
			else
			{
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				
				response.sendRedirect("index.jsp");
			}
		}
	}
}
