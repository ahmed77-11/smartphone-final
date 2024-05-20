package com.ahmed.smartphonejava.controllers;

import com.ahmed.smartphonejava.beans.Category;
import com.ahmed.smartphonejava.service.CategoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddCategoryController implements Initializable {

    @FXML
    private Label msg;

    @FXML
    private Button addBtn;

    @FXML
    private Button addCategoryBtn;

    @FXML
    private Button addSmartphoneBtn;

    @FXML
    private Button btnListSmartphone;

    @FXML
    private Button btnSignout;

    @FXML
    private Button categorieListBtn;


    @FXML
    private TextField descriptionTf;


    @FXML
    private TextField nameTf;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Button resetBtn;

    
    private CategoryService categoryService = new CategoryService();

    @FXML
    void addCategory(ActionEvent event) throws IOException {
        try {

            String name = nameTf.getText();
            String description = descriptionTf.getText();

            Category newCategory = new Category();
            newCategory.setNom(name);
            newCategory.setDescription(description);
            boolean res = categoryService.addCategory(newCategory);

            if (res) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/listCategory.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) addBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                msg.setText("Category not added. There is an error.");
            }
        } catch (Exception e) {
            msg.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    void onReset(ActionEvent event) {
        nameTf.clear();
        descriptionTf.clear();
        msg.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    
    }
    @FXML
    void redirecToListCategory(ActionEvent event) {

    }

    @FXML
    void redirecToListSmartphone(ActionEvent event) {

    }
}
