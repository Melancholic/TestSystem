package info.elfapp.testsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserQuestionsDAO {
	
	private DataSource dataSource;

	public UserQuestionsDAO(){
        try {
            Context initContext  = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/tsystemdb"); 
        } catch (NamingException e) {
            e.printStackTrace();
        }
	}
	
	public String getUserQuestions(int userID, int testID){
		String r = null;

        try {
			Connection connection = dataSource.getConnection();
	        String query = "SELECT Questions FROM UserQuestions WHERE UserID = ?";
	        PreparedStatement prepStmt = connection.prepareStatement(query);
	        prepStmt.setInt(1, userID);
	        ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
            	r = new String();
            	r = resultSet.getString("Questions");      
            }
            connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}	

}