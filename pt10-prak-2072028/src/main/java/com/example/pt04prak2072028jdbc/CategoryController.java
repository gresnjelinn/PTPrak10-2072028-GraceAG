package com.example.pt04prak2072028jdbc;
import com.example.pt04prak2072028jdbc.dao.CategoryDao;
import com.example.pt04prak2072028jdbc.model.Category;
import com.example.pt04prak2072028jdbc.model.CategoryEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoryController {

    public TextField inputIdCategory;
    public TextField inputNameCategory;
    public Button btnSaveCategory;
    public TableView tableCategory;
    public TableColumn columnIdCategory;
    public TableColumn columnNameCategory;

    ObservableList<CategoryEntity> cList;

    public void initialize() {
        CategoryDao dao = new CategoryDao();
        cList = FXCollections.observableArrayList(dao.getData());
        tableCategory.setItems(cList);
        columnIdCategory.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
        columnNameCategory.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
    }

    public void insertCategory(ActionEvent actionEvent) {
        CategoryDao dao = new CategoryDao();
        CategoryEntity c = new CategoryEntity();
        showAlert();
        c.setName(inputNameCategory.getText());
        dao.addData(c);
        cList = FXCollections.observableArrayList(dao.getData());
        tableCategory.setItems(cList);
        columnIdCategory.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
        columnNameCategory.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
    }

    public void showAlert() {
        if (inputIdCategory.getText().isEmpty() || inputNameCategory.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please fill in all the field");
            alert.setTitle("Message");
            alert.showAndWait();
        }
    }
}
