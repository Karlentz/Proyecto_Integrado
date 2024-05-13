package models;

import javafx.scene.control.Alert;

public class Messages {

    public static void showConfirmationMessage(String title, String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.show();
    }
}
