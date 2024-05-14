package controller;

import controller.connection.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Messages;

import java.sql.ResultSet;

public class LoginController extends Application {

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage login) {

    }

    public void iniciarSesion(){
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
