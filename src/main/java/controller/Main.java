package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.I18N;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static models.I18N.bundle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        I18N.setLocale(new Locale("es"));
        bundle = ResourceBundle.getBundle("login",I18N.getLocale());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        fxmlLoader.setResources(bundle);
        Parent root = fxmlLoader.load();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Icono.png")));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}