package models;

import javafx.scene.control.Alert;

public class Messages {

    public static void showConfirmationMessage(String title, String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.show();
    }

    public static void showWarningMessage(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.show();
    }

    public static void showErrorMessage(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.show();
    }
}
