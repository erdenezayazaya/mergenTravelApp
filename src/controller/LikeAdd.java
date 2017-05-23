package controller;

import java.io.IOException;
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
import dao.UserDAO;
import dao.UserDAOImpl;

@WebServlet("/LikeAdd")
public class LikeAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO dao;
	static Logger logger = Logger.getLogger(LikeAdd.class);

    public LikeAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ServletContext context = getServletContext();  
        Connection con = (Connection) context.getAttribute("DBConnection");
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		int like = Integer.parseInt(request.getParameter("like"));
		
		dao = new PostDAOImpl(con);
		
		dao.likeAdd(postId, like);
	}
}
