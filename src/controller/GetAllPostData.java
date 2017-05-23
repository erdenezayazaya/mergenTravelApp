package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import dao.PostDAO;
import dao.PostDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;

@WebServlet("/GetAllPostData")
public class GetAllPostData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO dao;
	
	static Logger logger = Logger.getLogger(Login.class);
   
    public GetAllPostData() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 ServletContext context = getServletContext();  
         Connection con = (Connection) context.getAttribute("DBConnection");
         
         dao = new PostDAOImpl(con);
         
         List<entity.Post> list = dao.getAllPost();
         
         Gson gson = new Gson();
         
         gson.toJson(list);
         
         response.setContentType("application/json");    
	     PrintWriter out = response.getWriter();
	     
	     out.print(gson.toJson(list));
	     System.out.println(gson.toJson(list));
	     //out.flush();
	}
}
