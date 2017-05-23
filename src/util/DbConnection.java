package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Singleton */
public class DbConnection {
	private Connection connection;
	
	public DbConnection(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(dbURL, user, pwd);
	}
	
	public Connection getConnection(){
		return this.connection;
	}
}
