package com.ahmed.smartphonejava.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.ahmed.smartphonejava.beans.Category;
import com.ahmed.smartphonejava.beans.Smartphone;
import com.ahmed.smartphonejava.service.CategoryService;
import com.ahmed.smartphonejava.service.SmartphoneService;

public class UpdateCategoryController implements Initializable {

    @FXML
    private Button addCategoryBtn;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnSignout;

    @FXML
    private TextField dateTf;

    @FXML
    private TextField descriptionTf;

    @FXML
    private TextField idTf;

    @FXML
    private Label msg;

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

    @FXML
    private Button updateBtn;
    
    ObservableList<String> listC= FXCollections.observableArrayList();

    Map<String,Integer> categoriesMap=new HashMap<>();

    CategoryService categoryService=new CategoryService();
    SmartphoneService smartphoneService=new SmartphoneService();

    public void setCategoryData(Category category) {
        idTf.setText(String.valueOf(category.getId()));
        nameTf.setText(category.getNom());
        descriptionTf.setText(category.getDescription());
    }
    
    
   

    @FXML
    void updateCategory(ActionEvent event) throws IOException {
        String idText=idTf.getText();
        int id=Integer.parseInt(idText);
        String brand=nameTf.getText();
        String model=descriptionTf.getText();

        boolean res=categoryService.updateCategory(new Category(id,brand,model));
        if (res) {
            Stage stage = (Stage) updateBtn.getScene().getWindow();
            stage.close();
        }else {
            System.out.println("Smartphone not added");
            msg.setText("Smartphone not added there is an error");
        }

    }

    @FXML
    void onReset() {
        // Logic to reset form fields goes here
    }
    
    
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic goes here
    }
}
