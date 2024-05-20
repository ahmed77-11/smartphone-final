package com.ahmed.smartphonejava.controllers;

import com.ahmed.smartphonejava.beans.Category;
import com.ahmed.smartphonejava.beans.Smartphone;
import com.ahmed.smartphonejava.service.CategoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ListeCategoryController implements Initializable {

    @FXML
    private Button addCateogryBtn;

    @FXML
    private Button addPhoneBtn;
    
    
    @FXML
    private Button phoneList;

    @FXML
    private Pane pnlCustomer;
    
    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableColumn<Smartphone, Integer> idCol;

    @FXML
    private Button btnSignout;

    @FXML
    private TableColumn<Smartphone, String> nameCol;

    @FXML
    private Button categoryListBtn;

    @FXML
    private TableColumn<Smartphone, Date> createdAtCol;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Smartphone, String> descriptionCol;

    @FXML
    private TextField searchPhone;

    @FXML
    private TableView<Category> tableS;

    @FXML
    private Button updateBtn;
    
    @FXML
    private Button searchBtn;

    private ObservableList<Category> listM = FXCollections.observableArrayList();
    
    private CategoryService categoryService = new CategoryService();

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
    void redirectToCategoryList(ActionEvent event) {
        // Already in the category list
    }

    @FXML
    void redrictToAddPhone(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/addSmartphone.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) addPhoneBtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signOut(ActionEvent event) {
        // Handle sign out logic
    }

    @FXML
    void searchBrand(ActionEvent event) {
        String keyword = searchPhone.getText();
        List<Category> list = categoryService.searchCategories(keyword);
        listM.clear();
        listM.addAll(list);
        tableS.setItems(listM);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<Smartphone,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("nom"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Smartphone,String>("description"));
        createdAtCol.setCellValueFactory(new PropertyValueFactory<Smartphone,Date>("create_at"));
        
        listM.addAll(categoryService.getAllCategories());
        tableS.setItems(listM);
    }

    @FXML
    void openUpdate(ActionEvent event) {
        Category category = tableS.getSelectionModel().getSelectedItem();
        if (category != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ahmed/smartphonejava/updateCategory.fxml"));
                Parent root = loader.load();

                UpdateCategoryController updateCategoryController = loader.getController();
                updateCategoryController.setCategoryData(category);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Update Category");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void removeCategory(ActionEvent event) {
        Category category = tableS.getSelectionModel().getSelectedItem();
        if (category != null) {
            boolean res = categoryService.deleteCategory(category);
            if (res) {
                listM.remove(category);
            } else {
                // Show error message
            }
        }
    }
}
