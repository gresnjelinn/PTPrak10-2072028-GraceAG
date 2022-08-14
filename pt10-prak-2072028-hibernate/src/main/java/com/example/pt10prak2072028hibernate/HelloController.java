package com.example.pt10prak2072028hibernate;

import com.example.pt10prak2072028hibernate.dao.CategoryDao;
import com.example.pt10prak2072028hibernate.dao.ItemsDao;
import com.example.pt10prak2072028hibernate.model.CategoryEntity;
import com.example.pt10prak2072028hibernate.model.ItemsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HelloController {
    public ListView lvCategory;
    public ListView lvItems;
    @FXML
    private Label welcomeText;
    private ObservableList<CategoryEntity> cList;
    private ObservableList<ItemsEntity> iList;

    public void initialize() {
        refresh();
    }

    public void refresh() {
        CategoryDao dao = new CategoryDao();
        cList = FXCollections.observableArrayList(dao.getData());
        lvCategory.setItems(cList);

        ItemsDao dao2 = new ItemsDao();
        iList = FXCollections.observableArrayList(dao2.getData());
        lvItems.setItems(iList);
    }

    @FXML
    protected void onHelloButtonClick() {
        CategoryDao dao = new CategoryDao();
        CategoryEntity c = new CategoryEntity();
        c.setName("Ini Kategori");
        dao.addData(c);
        refresh();
    }
}