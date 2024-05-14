package controller.connection;

import models.Messages;

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
        } catch (SQLIntegrityConstraintViolationException e) {
            Messages.showWarningMessage("Usuario ya existente","El correo introducido ya existe");
        } catch (SQLException e){
            Messages.showErrorMessage("Error en la inserción","Se ha producido un error intentando registrar la cuenta");
        } finally {
            return false;
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
