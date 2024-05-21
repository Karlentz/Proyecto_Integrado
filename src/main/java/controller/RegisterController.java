package controller;

import controller.connection.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.I18N;
import models.Messages;
import models.Validations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static models.I18N.bundle;

public class RegisterController {
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRepeatPassword;

    @FXML
    private Button goLoginButton;

    @FXML
    public void registerUser() {
        DatabaseConnector database = DatabaseConnector.getInstance();
        String user = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String repeatPassword = txtRepeatPassword.getText();

        if (Validations.validarEmail(email)){
            if (Validations.checkNull(user)){
                if (Validations.checkNull(password)){
                    if (password.equals(repeatPassword)){
                        try {
                            byte[] hashedPassword1 = hashPassword(password);
                            database.edit(
                                    "INSERT INTO client VALUES ('" + email + "', '" + user + "', '" + hashedPassword1.toString() + "')"
                            );
                            Messages.showConfirmationMessage("Bienvenido","Usuario creado");
                        }catch (Exception e){}
                    }else{
                        Messages.showWarningMessage("Contraseñas no coinciden","Las contraseñas no son iguales");
                    }
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

    // Método para obtener el hash de una contraseña usando SHA-256
    public static byte[] hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(password.getBytes());
        }catch (NoSuchAlgorithmException e){
            System.out.println("Error cifrando la contraseña");
            return null;
        }
    }

    public void goToLogin(){
        try {
            bundle = ResourceBundle.getBundle("login",I18N.getLocale());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            fxmlLoader.setResources(bundle);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Icono.png")));
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
            Stage myStage = (Stage) this.goLoginButton.getScene().getWindow();
            myStage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}