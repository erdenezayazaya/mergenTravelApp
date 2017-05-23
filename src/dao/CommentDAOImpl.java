package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Comment;
import entity.Post;
import entity.User;

public class CommentDAOImpl implements CommentDAO{
	
	private Connection conn;
	 
    public CommentDAOImpl(Connection connNew) {
    	conn = connNew;
    }

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		try
		{
			String query = "insert into comments(comment, idusers, idposts) values(?,?,?)";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, comment.getComment());
	        preparedStatement.setInt(2, comment.getUser().getIdusers());
	        preparedStatement.setInt(3, comment.getPost().getIdposts());	 
	       
	        preparedStatement.executeUpdate();
            preparedStatement.close();
            
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteComment(int idComments) {
		// TODO Auto-generated method stub
		 try {
           String query = "delete from comments where idusers=?";
           
           PreparedStatement preparedStatement = conn.prepareStatement(query);
           preparedStatement.setInt(1, idComments);
           preparedStatement.executeUpdate();
           
           preparedStatement.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
	}

	@Override
	public void updateComment(Comment comment) {
		// TODO Auto-generated method stub
		  try {	
            String query = "update comments set idusers=?, comment=?, idposts=? where idcomments=?";
		  
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            
            preparedStatement.setInt(1, comment.getUser().getIdusers());
	        preparedStatement.setString(2, comment.getComment());
	        preparedStatement.setInt(3, comment.getPost().getIdposts());
	        preparedStatement.setInt(4, comment.getIdcomments());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Comment> getAllComment() {
		// TODO Auto-generated method stub
		List<Comment> list = new ArrayList<Comment>();
		
        try {
            Statement statement = (Statement) conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from comments");
           
            while( resultSet.next() ) {
                Comment comment = new Comment();
                
                comment.setComment(resultSet.getString("comment"));
                comment.setIdcomments(resultSet.getInt("idcomments"));
//                comment.setPost(resultSet.getString("idposts"));
//                comment.setUser(resultSet.getString("idusers"));
//           
                list.add(comment);
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public List<Comment> getCommentByUserId(int idComments) {
		// TODO Auto-generated method stub
		List<Comment> list = new ArrayList<Comment>();
		
	        try {
	            String query = "select * from comments where idcomments=?";
	          
	            PreparedStatement preparedStatement = conn.prepareStatement(query);
	            preparedStatement.setInt(1, idComments);
	            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            while( resultSet.next() ) {
	        		Comment comment = new Comment();
	            	
	            	Post post = new Post();
	            	post.setIdposts(resultSet.getInt("idposts"));
	            	
	            	User user = new User();
	            	user.setIdusers(resultSet.getInt("idusers"));
	            	
	            	comment.setComment(resultSet.getString("comment"));
	            	comment.setIdcomments(resultSet.getInt("idcomments"));
	            	
	            	list.add(comment);
	            }
	            
	            resultSet.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
        return list;
	}
	
}
