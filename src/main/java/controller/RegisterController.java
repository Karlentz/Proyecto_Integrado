package controller;

import controller.connection.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Messages;

import java.io.IOException;

public class RegisterController {

    private Stage register;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    public void registerUser() {
        DatabaseConnector database = DatabaseConnector.getInstance();
        String user = txtNombre.getText();
        String email = txtCorreo.getText();
        String contraseña = txtContraseña.getText();

        if(database.insert(
                "INSERT INTO client VALUES ('" + email + "', '" + user + "', '" + contraseña + "')"
        )){
            Messages.showConfirmationMessage("Operacion exitosa","La operacion se hizo con éxito");
        }
    }

    public void setFxml(Stage stage) {
        this.register = stage;
    }

    public void goToLogin(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            Stage loginFxml = new Stage();
            loginFxml.setTitle("Login");
            loginFxml.setScene(new Scene(root));
            register.hide();
            loginFxml.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}