package com.ahmed.smartphonejava.controllers;

import com.ahmed.smartphonejava.beans.User;
import com.ahmed.smartphonejava.exception.ExistUserException;
import com.ahmed.smartphonejava.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    private Stage stage;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField username;

    UserService userService = new UserService();

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void signUp(ActionEvent event) {
        User user = new User();
        user.setEmail(email.getText());
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        try {
            userService.register(user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/login.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the stage from the signUpBtn
            Stage stage = (Stage) signUpBtn.getScene().getWindow();

            // Set the scene to the stage
            stage.setScene(scene);
            stage.show();

        } catch (ExistUserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
