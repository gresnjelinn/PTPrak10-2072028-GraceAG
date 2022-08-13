package com.example.pt10prak2072028hibernate;

import com.example.pt10prak2072028hibernate.dao.CategoryDao;
import com.example.pt10prak2072028hibernate.model.CategoryEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HelloController {
    public ListView lvCategory;
    @FXML
    private Label welcomeText;
    private ObservableList<CategoryEntity> cList;

    public void initialize() {
        refresh();
    }

    public void refresh() {
        CategoryDao dao = new CategoryDao();
        cList = FXCollections.observableArrayList(dao.getData());
        lvCategory.setItems(cList);
    }

    @FXML
    protected void onHelloButtonClick() {
        CategoryDao dao = new CategoryDao();
        CategoryEntity c = new CategoryEntity();
        c.setId(7);
        c.setName("Ini Kategori");
        dao.addData(c);
        refresh();
    }
}