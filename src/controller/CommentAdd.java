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

import dao.CommentDAO;
import dao.CommentDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entity.Comment;
import entity.User;

@WebServlet("/CommentAdd")
public class CommentAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO dao;
	static Logger logger = Logger.getLogger(CommentAdd.class);
	
    public CommentAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ServletContext context = getServletContext();  
	     Connection con = (Connection) context.getAttribute("DBConnection");
	     dao = new CommentDAOImpl(con);
	     
	     int userId = Integer.parseInt(request.getParameter("userId"));
	     int postId = Integer.parseInt(request.getParameter("postId"));
	     String commentText = request.getParameter("commentText");
	     
	     User user = new User();
	     user.setIdusers(userId);
	     
	     entity.Post post = new entity.Post();
	     post.setIdposts(postId);
	     
	     Comment comment = new Comment();
	     
	     comment.setComment(commentText);
	     comment.setPost(post);
	     comment.setUser(user);
	     
	     dao.addComment(comment);
	}

}
