package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entity.Comment;
import entity.Post;
import entity.User;

public class PostDAOImpl implements PostDAO{
	
	private Connection conn;
	 
    public PostDAOImpl(Connection connNew) {
    	conn = connNew;
    }

	@Override
	public void addPost(Post post) {
		// TODO Auto-generated method stub
		try
		{
	       String query = "insert into posts(idusers, post, imageName, createdDate) values(?,?,?,?)";
			
	        Date date = new Date(Calendar.getInstance().getTime().getTime());
	       
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setInt(1, post.getUser().getIdusers());
	        preparedStatement.setString(2, post.getPost());
	        preparedStatement.setString(3, post.getImageName());	
	        preparedStatement.setDate(4, date);
	       
	        preparedStatement.executeUpdate();
            preparedStatement.close();
            
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deletePost(int idPosts) {
		// TODO Auto-generated method stub
		 try {
	           String query = "delete from posts where idPosts=?";
	           
	           PreparedStatement preparedStatement = conn.prepareStatement(query);
	           preparedStatement.setInt(1, idPosts);
	           preparedStatement.executeUpdate();
	           
	           preparedStatement.close();
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	}

	@Override
	public void updatePost(Post post) {
		// TODO Auto-generated method stub
		  try {	
          String query = "update posts set post=? where idposts=?";
		  
          PreparedStatement preparedStatement = conn.prepareStatement(query);
          
          preparedStatement.setString(1, post.getPost());
	      preparedStatement.setInt(2, post.getIdposts());
          
          preparedStatement.executeUpdate();
          preparedStatement.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
	}

	@Override
	public List<Post> getAllPost() {
		List<Post> list = new ArrayList<Post>();
		
        try {
            Statement statement = (Statement) conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery("select s.idusers, s.fullname, p.idposts, "
            		+ "p.imageName, p.likeCount, p.post from posts p "
            		+ "inner join users s "
            		+ "on p.idusers = s.idusers "
            		+ "order by p.createdDate desc ");
           
            while( resultSet.next() ) {
            	Post post = new Post();
            	
            	post.setIdposts(resultSet.getInt("idposts"));
        
            	User user = new User();
            	user.setIdusers(resultSet.getInt("idusers"));
            	user.setFullname(resultSet.getString("fullname"));
            	
            	post.setUser(user);
           
            	post.setLike(resultSet.getInt("likeCount"));
            	post.setPost(resultSet.getString("post"));
            	post.setImageName(resultSet.getString("imageName"));
            	
            	List<Comment> commentList = getCommentByPosID(post.getIdposts());
            	
            	post.setListComments(commentList);
            	
                list.add(post);
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}
	
	public List<Comment> getCommentByPosID(int idPosts)
	{
		// TODO Auto-generated method stub
		List<Comment> list = new ArrayList<Comment>();
		
	        try {	        	
	            String query = "select c.idcomments, c.comment, c.idposts, c.idusers, u.fullname from comments c "
	            		+ "inner join users u "
	            		+ "on c.idusers = u.idusers "
	            		+ "where idposts=?";
	          
	            PreparedStatement preparedStatement = conn.prepareStatement(query);
	            preparedStatement.setInt(1, idPosts);
	            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            while(resultSet.next()) {
	            	Comment comment = new Comment();
	            	
	            	comment.setComment(resultSet.getString("comment"));
	            	comment.setIdcomments(resultSet.getInt("idcomments"));
	            	
	            	entity.Post post = new Post();
	            	post.setIdposts(idPosts);
	            	
	            	comment.setPost(post);
	            	
	            	User user = new User();
	            	user.setIdusers(resultSet.getInt("idusers"));
	            	user.setFullname(resultSet.getString("fullname"));
	            	
	            	comment.setUser(user);
	            	
	                list.add(comment);
	            }
	            
	            resultSet.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
        return list;
	}

	@Override
	public List<Post> getPostByUser(int idusers){
		// TODO Auto-generated method stub
		List<Post> list = new ArrayList<Post>();
		
	        try {
	            String query = "select * from posts where idcomments=?";
	          
	            PreparedStatement preparedStatement = conn.prepareStatement(query);
	            preparedStatement.setInt(1, idusers);
	            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            while(resultSet.next()) {
	            	Post post = new Post();
	            	
	            	post.setIdposts(resultSet.getInt("idposts"));
	            	
	            	User user = new User();
	            	user.setIdusers(resultSet.getInt("idusers"));
	            	
	            	post.setUser(user);
	  
	            	post.setLike(resultSet.getInt("like"));
	            	post.setPost(resultSet.getString("post"));
	            	post.setImageName(resultSet.getString("imageName"));

	                list.add(post);
	            }
	            
	            resultSet.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
        return list;
	}

	@Override
	public void likeAdd(int idPosts, int likeCount) {
		// TODO Auto-generated method stub
	  try {	
        String query = "update posts set likeCount=? where idposts=?";
		  
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        
        likeCount++;
        
        preparedStatement.setInt(1, likeCount);
	    preparedStatement.setInt(2, idPosts);
        
        preparedStatement.executeUpdate();
        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public int getPostCount() {		
		
		int postCount = 0;
		
        try {
            Statement statement = (Statement) conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery("select count(idusers) as postNumber from posts");
           
            while(resultSet.next()) {
            	postCount = resultSet.getInt("postNumber");
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return postCount;
	}

}
