package controller;

import controller.connection.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Messages;
import models.Validations;

import java.io.IOException;

public class RegisterController extends Application {

    private Stage register;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void registerUser() {
        DatabaseConnector database = DatabaseConnector.getInstance();
        String user = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (Validations.validarEmail(email)){
            if (Validations.checkNull(user)){
                if (Validations.checkNull(password)){
                    try {
                        database.insert(
                                "INSERT INTO client VALUES ('" + email + "', '" + user + "', '" + password + "')"
                        );
                        Messages.showConfirmationMessage("Bienvenido","Usuario creado");
                    }catch (Exception e){}
                }else{
                    Messages.showWarningMessage("Contraseña vacía","La contraseña no puede estar vacía");
                }
            }else{
                Messages.showWarningMessage("Usuario vacío","EL usuario que ha introducido no puede estar vacío");
            }
        }else{
            Messages.showWarningMessage("Correo incorrecto","EL correo que ha introducido no es válido, por favor, revíselo");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

    public void setFxml(Stage stage) {
        this.register = stage;
    }

    public void goToLogin(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Icono.png")));
            stage.setTitle("Login");
            stage.setScene(new Scene(root1));
            stage.show();
            register.hide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}