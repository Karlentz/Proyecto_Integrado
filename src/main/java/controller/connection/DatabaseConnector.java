package controller.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

public class DatabaseConnector implements Closeable {

    private static DatabaseConnector instance;
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/vacations?serverTimezone=UTC";
    private PreparedStatement statement;

    private Connection connection;

    private DatabaseConnector(){
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseConnector getInstance(){
        return instance == null ?  new DatabaseConnector() : instance;
    }

    public ResultSet consult(String query){
        try {
            this.statement = connection.prepareStatement(query);
            return this.statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insert(String query){
        try {
            this.statement = connection.prepareStatement(query);
            this.statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (!connection.isClosed()){
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
