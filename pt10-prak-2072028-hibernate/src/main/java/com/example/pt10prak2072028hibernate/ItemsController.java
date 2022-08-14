package com.example.pt10prak2072028hibernate;

import com.example.pt10prak2072028hibernate.dao.CategoryDao;
import com.example.pt10prak2072028hibernate.dao.ItemsDao;
import com.example.pt10prak2072028hibernate.model.CategoryEntity;
import com.example.pt10prak2072028hibernate.model.ItemsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ItemsController {
    public Menu menuFile;
    public MenuItem menuItemCategory;
    public TextField inputIdItems;
    public TextField InputNameItems;
    public TextField InputPriceItems;
    public TextField InputDescriptionItems;
    public ComboBox comboboxCategoryItems;
    public Button btnInsertItems;
    public Button btnResetItems;
    public Button btnUpdateItems;
    public Button btnDeleteItems;
    public TableView tableItems;
    public TableColumn columnIdItems;
    public TableColumn columnNameItems;
    public TableColumn columnPriceItems;
    public TableColumn columnCategoryItems;

    public Stage categoryStage;
    public MenuItem menuItemClose;
    public Menu menuReport;
    public MenuItem menuItemSimple;
    public MenuItem menuItemGroup;
    private ObservableList<CategoryEntity> cList;
    private ObservableList<ItemsEntity> iList;

    public void initialize() {
        CategoryDao dao = new CategoryDao();
        cList = FXCollections.observableArrayList(dao.getData());
        comboboxCategoryItems.setItems(cList);

        ItemsDao dao2 = new ItemsDao();
        iList = FXCollections.observableArrayList(dao2.getData());
        tableItems.setItems(iList);
        columnIdItems.setCellValueFactory(new PropertyValueFactory<ItemsEntity, Integer>("id"));
        columnNameItems.setCellValueFactory(new PropertyValueFactory<ItemsEntity, String>("name"));
        columnPriceItems.setCellValueFactory(new PropertyValueFactory<ItemsEntity, Double>("price"));
        columnCategoryItems.setCellValueFactory(new PropertyValueFactory<ItemsEntity, String>("category"));
    }

    public void goToCategory(ActionEvent actionEvent) {
    }

    public void closeItemsWindow(ActionEvent actionEvent) {
    }

    public void goSimpleReport(ActionEvent actionEvent) {
    }

    public void goGroupReport(ActionEvent actionEvent) {
    }

    public void insertItems(ActionEvent actionEvent) {
    }

    public void resetInput(ActionEvent actionEvent) {
    }

    public void updateItems(ActionEvent actionEvent) {
    }

    public void deleteItems(ActionEvent actionEvent) {
    }

    public void rowSelected(MouseEvent mouseEvent) {
    }
}
