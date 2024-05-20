package com.ahmed.smartphonejava.controllers;

import com.ahmed.smartphonejava.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static com.ahmed.smartphonejava.assets.ShowError.showErrorMessage;

public class LoginController {
    private Stage stage;

    public LoginController() {
        super();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Label msg;
    @FXML
    private Label msgSuccess;
    @FXML
    private TextField email;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private Button signUpBtn;


    UserService userService=new UserService();

    @FXML
    void logIn(ActionEvent event) throws IOException {
        String v_email=email.getText();
        String v_password=password.getText();
        String v_msg=userService.login(v_email,v_password);
        if(v_msg.compareTo("Log in Succeffuly")==0){
            msgSuccess.setText(v_msg);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/listSmartphone.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the stage from the loginBtn
            Stage stage = (Stage) loginBtn.getScene().getWindow();

            // Set the scene to the stage
            stage.setScene(scene);
            stage.show();
        }else{
            msg.setText(v_msg);
            showErrorMessage(v_msg);
        }
    }

    @FXML
    void redirectLogIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/ahmed/smartphonejava/signup.fxml"));
        Parent root = loader.load();

        // Create a new scene with the loaded FXML file
        Scene scene = new Scene(root);

        // Set the scene to the stage
        stage.setScene(scene);
        stage.show();
    }



}