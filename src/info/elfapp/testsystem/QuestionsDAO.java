package info.elfapp.testsystem;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QuestionsDAO {

    private DataSource dataSource;

    public QuestionsDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/tsystemdb");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String getUserQuestion(int questionID) {
        String r = null;

        try {
            String QUESTION = "Question";
            Connection connection = dataSource.getConnection();
            String query = "SELECT " + QUESTION + " FROM Questions WHERE ID = ?";
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1, questionID);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                r = new String();
                r = resultSet.getString(QUESTION);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

}