package com.ahmed.smartphonejava.controllers;

import com.ahmed.smartphonejava.beans.Category;
import com.ahmed.smartphonejava.beans.Smartphone;
import com.ahmed.smartphonejava.service.CategoryService;
import com.ahmed.smartphonejava.service.SmartphoneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AddSmartphoneController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private Button addBtn;

    @FXML
    private TextField brandTf;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnSignout1;

    @FXML
    private ComboBox<String> catCb;

    @FXML
    private TextField modelTf;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField priceTf;

    @FXML
    private Button resetBtn;

    private Map<String,Integer> categoriesMap=new HashMap<>();
    CategoryService categoryService=new CategoryService();
    SmartphoneService smartphoneService=new SmartphoneService();

    @FXML
    void addSmarthone(ActionEvent event) throws IOException {
        String brand=brandTf.getText();
        String model=modelTf.getText();
        String priceTfText=priceTf.getText();
        String categoryName=catCb.getValue();
        double price=Double.parseDouble(priceTfText);
        int categoryId=categoriesMap.get(categoryName);
        Category category1=categoryService.getCategoryById(categoryId);
        boolean res=smartphoneService.addSmartphone(new Smartphone(brand,model,price,category1));
        if (res) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/listSmartphone.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the stage from the loginBtn
            Stage stage = (Stage) addBtn.getScene().getWindow();

            // Set the scene to the stage
            stage.setScene(scene);
            stage.show();
        }else {
            System.out.println("Smartphone not added");
            msg.setText("Smartphone not added there is an error");
        }
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void onReset(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listE = FXCollections.observableArrayList();
        List<Category> list=categoryService.getAllCategories();
        for (Category c:list){
            System.out.println(c.getNom());
            listE.add(c.getNom());
        }
        catCb.setItems(listE);
        for (Category c:list){
            categoriesMap.put(c.getNom(),c.getId());
        }
    }
}
