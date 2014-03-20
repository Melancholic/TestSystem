package info.elfapp.testsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	
	public class UserBean{
		int ID;
		String Name;
		String Password;
		String Session;
		
		public int getID(){
			return ID;
		}
		public String getName(){
			return Name;
		}
		public String getPassword(){
			return Password;
		}
		public String getSession(){
			return Session;
		}
	}
	
	private DataSource dataSource;

	public UserDAO(){
        try {
            Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/tsystemdb"); 
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}

	public UserBean getUserByID(int id){
		UserBean user = null;
        try {
			Connection connection = dataSource.getConnection();
	        String query = "SELECT ID, Name, Password, Session FROM Users WHERE ID = ?";
	        PreparedStatement prepStmt = connection.prepareStatement(query);
	        prepStmt.setInt(1, id);
	        ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
            	user = new UserBean();
            	user.ID = resultSet.getInt("ID");
                user.Name = resultSet.getString("Name");
                user.Password = resultSet.getString("Password");
                user.Session = resultSet.getString("Session");
            }
            connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public UserBean getUserByName(String name){
		UserBean user = null;
		if(name!=null){
	        try {
				Connection connection = dataSource.getConnection();
		        String query = "SELECT ID, Name, Password, Session FROM Users WHERE Name = ?";
		        PreparedStatement prepStmt = connection.prepareStatement(query);
		        prepStmt.setString(1, name);
		        ResultSet resultSet = prepStmt.executeQuery();
	            if (resultSet.next()) {
	            	user = new UserBean();
	            	user.ID = resultSet.getInt("ID");
	                user.Name = resultSet.getString("Name");
	                user.Password = resultSet.getString("Password");
	                user.Session = resultSet.getString("Session");
	            }
	            connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public void setUserSession(int id, String session) {
		try {
			Connection connection = dataSource.getConnection();
			String query = "UPDATE Users SET Session=? WHERE ID=?";
	        PreparedStatement prepStmt = connection.prepareStatement(query);
	        prepStmt.setString(1, session);
			prepStmt.setInt(2, id);
	        prepStmt.executeUpdate();
	        connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
