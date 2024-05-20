package com.ahmed.smartphonejava.assets;

import javafx.scene.control.Alert;

public class ShowError {
    public static void showErrorMessage(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Search Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
