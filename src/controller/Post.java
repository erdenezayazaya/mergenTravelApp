package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import dao.PostDAO;
import dao.PostDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entity.User;

@WebServlet("/Post")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO dao;
	static Logger logger = Logger.getLogger(Post.class);
	
	private String filePath;
	   
    public Post() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ServletContext context = getServletContext();  
	     Connection con = (Connection) context.getAttribute("DBConnection");
	     String postContent = request.getParameter("postContent");
		
		filePath = getServletContext().getInitParameter("file-upload"); 
        String name = "";
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        name = new File(item.getName()).getName();
                        item.write( new File(filePath + File.separator + name));
                    }
                    else
                    	postContent = item.getString();
                }
                
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("User");
                
                entity.Post post = new entity.Post();
                
                post.setImageName(name);
                post.setPost(postContent);
                post.setUser(user);
                
                dao = new PostDAOImpl(con);
                
                dao.addPost(post);
            } 
            catch (Exception ex) {
              
            }  
        }
	      
	}
}
