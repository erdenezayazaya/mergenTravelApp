package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.PostDAO;
import dao.PostDAOImpl;

@WebServlet("/GetBellFunction")
public class GetBellFunction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO dao;
	static Logger logger = Logger.getLogger(GetBellFunction.class);
	
    public GetBellFunction() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ServletContext context = getServletContext();  
	     Connection con = (Connection) context.getAttribute("DBConnection");
	     dao = new PostDAOImpl(con);
	     
	     int postCount = dao.getPostCount();
	     
	     logger.info(postCount);
	     
	     PrintWriter writer = response.getWriter();

	     logger.info("Post Count: " + postCount);
	     
	     writer.println(postCount);
	}

}
