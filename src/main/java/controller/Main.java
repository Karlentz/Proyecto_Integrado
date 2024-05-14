package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
        Parent root = fxmlLoader.load();
        RegisterController register = fxmlLoader.getController();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Icono.png")));
        register.setFxml(stage);
        stage.setTitle("Register");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}