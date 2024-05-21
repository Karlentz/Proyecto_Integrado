package controller;

import controller.connection.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.I18N;
import models.Messages;

import java.sql.ResultSet;
import java.util.ResourceBundle;

import static models.I18N.bundle;

public class LoginController{

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button goRegisterButton;

    @FXML
    public void goToRegister(){
        try {
            bundle = ResourceBundle.getBundle("register",I18N.getLocale());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
            fxmlLoader.setResources(bundle);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Icono.png")));
            stage.setTitle("Register");
            stage.setScene(new Scene(root));
            stage.show();
            Stage myStage = (Stage) this.goRegisterButton.getScene().getWindow();
            myStage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logIn(){
        DatabaseConnector database = DatabaseConnector.getInstance();
        String email = txtEmail.getText();
        String contraseña = txtPassword.getText();
        try {
            ResultSet rs = database.consult(
                    "SELECT * FROM client WHERE email = '" + email + "' AND password = '" + contraseña + "'"
            );
            if (rs.next()){
                System.out.printf(rs.getString(1) + " " + rs.getString(2));
            }else{
                Messages.showWarningMessage("Usuario no encontrado","No se ha encontrado el usuario y/o la contraseña, por favor, revise los datos");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
