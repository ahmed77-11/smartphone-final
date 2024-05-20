package com.ahmed.smartphonejava.controllers;

import com.ahmed.smartphonejava.assets.ShowError;
import com.ahmed.smartphonejava.beans.Category;
import com.ahmed.smartphonejava.beans.Smartphone;
import com.ahmed.smartphonejava.service.CategoryService;
import com.ahmed.smartphonejava.service.SmartphoneService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ListeSmartphoneController implements Initializable {

    @FXML
    private Button addCateogryBtn;

    @FXML
    private Button addPhoneBtn;

    @FXML
    private TableColumn<Smartphone, String> brandCol;

    @FXML
    private Button btnSignout;

    @FXML
    private TableColumn<Smartphone, String> categoryCol;

    @FXML
    private Button categoryListBtn;

    @FXML
    private TableColumn<Smartphone, Date> createdAtCol;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Smartphone, String> modelCol;

    @FXML
    private Button phoneList;

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Button searchBtn;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableColumn<Smartphone, Double> priceCol;

    @FXML
    private TextField searchPhone;

    @FXML
    private TableView<Smartphone> tableS;

    @FXML
    private Button updateBtn;
    @FXML
    private ComboBox<String> findByCatCb;
    @FXML
    private Button findByCatBtn;
    ObservableList<Smartphone> listM = FXCollections.observableArrayList();
    ObservableList<String> listMCb=FXCollections.observableArrayList();

    Map<String,Integer> categoryMap=new HashMap<>();

    SmartphoneService smartphoneService=new SmartphoneService();

    CategoryService categoryService=new CategoryService();

    @FXML
    void redirectToAddCateogry(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/addCategory.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) addCateogryBtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void redirectToCategoryList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/listCategory.fxml"));
        Parent root = loader.load();

        // Create a new scene with the loaded FXML file
        Scene scene = new Scene(root);

        // Get the stage from the loginBtn
        Stage stage = (Stage) categoryListBtn.getScene().getWindow();

        // Set the scene to the stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void redrictToAddPhone(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/addSmartphone.fxml"));
        Parent root = loader.load();

        // Create a new scene with the loaded FXML file
        Scene scene = new Scene(root);

        // Get the stage from the loginBtn
        Stage stage = (Stage) addPhoneBtn.getScene().getWindow();

        // Set the scene to the stage
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void signOut(ActionEvent event) {

    }
    @FXML
    public void searchBrand(ActionEvent event) {
        System.out.println(searchPhone.getText());
        List<Smartphone> list = smartphoneService.getSmartphonesByBrand(searchPhone.getText());
        listM.clear(); // Clear the existing items in the observable list
        if (!list.isEmpty()) {
            listM.addAll(list);
            tableS.setItems(listM);
        } else {
            List<Smartphone> list1 =smartphoneService.getAllSmartphones();
            listM.addAll(list1);
            tableS.setItems(listM);

            System.out.println("No smartphones found for the given brand.");
        }
    }

    @FXML
    void findByCategories(ActionEvent event) {
        String catName=findByCatCb.getValue();
        int catId=categoryMap.get(catName);
        List<Smartphone> list=smartphoneService.getSmartphonesByCat(catId);
        listM.clear();
        if (!list.isEmpty()) {
            listM.addAll(list);
            tableS.setItems(listM);
        } else {
            List<Smartphone> list1 =smartphoneService.getAllSmartphones();
            listM.addAll(list1);
            tableS.setItems(listM);

            ShowError.showErrorMessage("No smartphones found for the given brand.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brandCol.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("brand"));
        modelCol.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("model"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Smartphone,Double>("price"));
        createdAtCol.setCellValueFactory(new PropertyValueFactory<Smartphone,Date>("created_at"));
        categoryCol.setCellValueFactory(cellData -> {
            Smartphone smartphone = cellData.getValue();
            Category category = smartphone.getCategory();
            if (category != null) {
                return new SimpleStringProperty(category.getNom());
            } else {
                return new SimpleStringProperty("");
            }
        });
        listM.addAll(smartphoneService.getAllSmartphones());
        tableS.setItems(listM);
        System.out.println(listM);
        List<Category> list=categoryService.getAllCategories();
        for (Category c:list){
            System.out.println(c.getNom());
            listMCb.add(c.getNom());
        }
        findByCatCb.setItems(listMCb);
        for (Category c:list){
            categoryMap.put(c.getNom(),c.getId());
        }

    }
    @FXML
    void openUpdate(ActionEvent event) {
        Smartphone smartphone=tableS.getSelectionModel().getSelectedItem();
        System.out.println(smartphone);
        try {
            // Load the FXML file for the detail view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/updateSmartphone.fxml"));
            Parent root = loader.load();

            // Get the controller for the detail view
            UpdateSmartphoneController updateSmartphoneController = loader.getController();
            // Pass data to the detail controller
            updateSmartphoneController.setSmartphoneData(smartphone);
            // Create a new scene with the loaded FXML file
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Update View");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void removeSmartphone(ActionEvent event) {
        Smartphone smartphone=tableS.getSelectionModel().getSelectedItem();
        boolean res= smartphoneService.deleteSmartphone(smartphone);
        System.out.println(res);
        if(!res){
            ShowError.showErrorMessage("there is an error");
        }
    }
}
