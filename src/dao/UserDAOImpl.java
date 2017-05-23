package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDAOImpl implements UserDAO{
	
	private Connection conn;
	 
    public UserDAOImpl(Connection connNew) {
    	conn = connNew;
    }

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		try
		{
			String query = "insert into users (fullname, gender, state, city, "
					+ "street, zipcode, birthyear, email, password) values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, user.getFullname());
	        preparedStatement.setString(2, user.getGender());
	        preparedStatement.setString(3, user.getState());
	        preparedStatement.setString(4, user.getCity());
	        preparedStatement.setString(5, user.getStreet());
	        preparedStatement.setString(6, user.getZipcode());
	        preparedStatement.setString(7, user.getBirthyear());
	        preparedStatement.setString(8, user.getEmail());
	        preparedStatement.setString(9, user.getPassword());
	       
	        preparedStatement.executeUpdate();
            preparedStatement.close();
            
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteUser(int idUsers) {
		// TODO Auto-generated method stub
		 try {
            String query = "delete from users where idusers=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, idUsers);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		  try {	
            String query = "update users set fullname=?, gender=?, state=?, city=?"
            		+ ", street=?, zipcode=?, birthyear=?, email=? where email=?";
		  
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            
            preparedStatement.setString(1, user.getFullname());
	        preparedStatement.setString(2, user.getGender());
	        preparedStatement.setString(3, user.getState());
	        preparedStatement.setString(4, user.getCity());
	        preparedStatement.setString(5, user.getStreet());
	        preparedStatement.setString(6, user.getZipcode());
	        preparedStatement.setString(7, user.getBirthyear());
	        preparedStatement.setString(8, user.getEmail());
            preparedStatement.setString(9, user.getEmail());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		
        try {
            Statement statement = (Statement) conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
           
            while( resultSet.next() ) {
                User user = new User();
                
                user.setBirthyear(resultSet.getString("birthyear"));
                user.setCity(resultSet.getString("city"));
                user.setEmail(resultSet.getString("email"));
                user.setFullname(resultSet.getString("fullname"));
                user.setGender(resultSet.getString("gender"));
                user.setIdusers(resultSet.getInt("idusers"));
                user.setPassword(resultSet.getString("password"));
                user.setState(resultSet.getString("state"));
                user.setStreet(resultSet.getString("street"));
                user.setZipcode(resultSet.getString("zipcode"));
                
                list.add(user);
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		 User user = null;
	        try {
	            String query = "select * from users where email=?";
	          
	            PreparedStatement preparedStatement = conn.prepareStatement( query );
	            preparedStatement.setString(1, email);
	            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            while(resultSet.next()) {
	            	 user = new User();
	            	 user.setBirthyear(resultSet.getString("birthyear"));
	                 user.setCity(resultSet.getString("city"));
	                 user.setEmail(resultSet.getString("email"));
	                 user.setFullname(resultSet.getString("fullname"));
	                 user.setGender(resultSet.getString("gender"));
	                 user.setIdusers(resultSet.getInt("idusers"));
	                 user.setPassword(resultSet.getString("password"));
	                 user.setState(resultSet.getString("state"));
	                 user.setStreet(resultSet.getString("street"));
	                 user.setZipcode(resultSet.getString("zipcode"));
	            }
	            
	            resultSet.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
        return user;
	}

}
