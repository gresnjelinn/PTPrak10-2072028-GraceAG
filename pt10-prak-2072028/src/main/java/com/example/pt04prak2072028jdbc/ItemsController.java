package com.example.pt04prak2072028jdbc;

import com.example.pt04prak2072028jdbc.dao.CategoryDao;
import com.example.pt04prak2072028jdbc.dao.ItemsDao;
import com.example.pt04prak2072028jdbc.model.CategoryEntity;
import com.example.pt04prak2072028jdbc.model.Items;
import com.example.pt04prak2072028jdbc.model.ItemsEntity;
import com.example.pt04prak2072028jdbc.util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

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

    ObservableList<ItemsEntity> iList;
    ObservableList<CategoryEntity> cList;

    public void initialize() {
        CategoryDao dao = new CategoryDao();
        cList = FXCollections.observableArrayList(dao.getData());
        comboboxCategoryItems.setItems(cList);

        ItemsDao dao2 = new ItemsDao();
        iList = FXCollections.observableArrayList(dao2.getData());
        tableItems.setItems(iList);
        columnIdItems.setCellValueFactory(new PropertyValueFactory<Items, Integer>("id"));
        columnNameItems.setCellValueFactory(new PropertyValueFactory<Items, String>("name"));
        columnPriceItems.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));
        columnCategoryItems.setCellValueFactory(new PropertyValueFactory<Items, String>("category"));

        cList = FXCollections.observableArrayList(dao.getData());
        comboboxCategoryItems.setItems(cList);

        menuItemCategory.setAccelerator(KeyCombination.keyCombination("Alt+F2"));
        menuItemClose.setAccelerator(KeyCombination.keyCombination("Alt+X"));
        menuItemSimple.setAccelerator(KeyCombination.keyCombination("Alt+S"));
        menuItemGroup.setAccelerator(KeyCombination.keyCombination("Alt+G"));
    }

    public void goToCategory(ActionEvent actionEvent) throws IOException {
        categoryStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("category-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 291, 357);
        categoryStage.setTitle("Category Management");
        categoryStage.setScene(scene);
        categoryStage.showAndWait();
    }

    public void resetInput(ActionEvent actionEvent) {
        inputIdItems.setText("");
        InputNameItems.setText("");
        InputPriceItems.setText("");
        InputDescriptionItems.setText("");
        comboboxCategoryItems.getSelectionModel().select(-1);
    }

    public void insertItems(ActionEvent actionEvent) {
        ItemsDao dao = new ItemsDao();
        ItemsEntity i = new ItemsEntity();
        showAlert();
        i.setId(Integer.parseInt(inputIdItems.getText()));
        i.setName(InputNameItems.getText());
        i.setPrice(Double.parseDouble(InputPriceItems.getText()));
        i.setDescription(InputDescriptionItems.getText());
        i.setCategoryByCategoryId(cList.get(comboboxCategoryItems.getSelectionModel().getSelectedIndex()));
        dao.addData(i);
        iList = FXCollections.observableArrayList(dao.getData());
        refreshData(iList);
    }

    public void updateItems(ActionEvent actionEvent) {
        ItemsDao dao = new ItemsDao();
        int selectedRow = tableItems.getSelectionModel().getSelectedIndex();
        ItemsEntity selectedItems = iList.get(selectedRow);
        showAlert();
        selectedItems.setName(InputNameItems.getText());
        selectedItems.setPrice(Double.parseDouble(InputPriceItems.getText()));
        selectedItems.setDescription(InputDescriptionItems.getText());
        selectedItems.setCategoryByCategoryId(cList.get(comboboxCategoryItems.getSelectionModel().getSelectedIndex()));
        dao.updateData(selectedItems);
        iList = FXCollections.observableArrayList(dao.getData());
        refreshData(iList);
    }

    public void deleteItems(ActionEvent actionEvent) {
        ItemsDao dao = new ItemsDao();
        int selectedRow = tableItems.getSelectionModel().getSelectedIndex();
        dao.delData(iList.get(selectedRow));
        iList = FXCollections.observableArrayList(dao.getData());
        refreshData(iList);
    }

    public void rowSelected(MouseEvent mouseEvent) {
        int selectedRow = tableItems.getSelectionModel().getSelectedIndex();
        inputIdItems.setText(String.valueOf(iList.get(selectedRow).getId()));
        InputNameItems.setText(iList.get(selectedRow).getName());
        InputPriceItems.setText(String.valueOf(iList.get(selectedRow).getPrice()));
        InputDescriptionItems.setText(iList.get(selectedRow).getDescription());
        comboboxCategoryItems.setValue(iList.get(selectedRow).getCategoryByCategoryId());
    }

    public void refreshData(ObservableList<ItemsEntity> iList) {
        tableItems.setItems(iList);
        columnIdItems.setCellValueFactory(new PropertyValueFactory<Items, Integer>("id"));
        columnNameItems.setCellValueFactory(new PropertyValueFactory<Items, String>("name"));
        columnPriceItems.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));
        columnCategoryItems.setCellValueFactory(new PropertyValueFactory<Items, String>("category"));
    }

    public void closeItemsWindow(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void showAlert() {
        if (inputIdItems.getText().isEmpty() || InputNameItems.getText().isEmpty() ||
                InputPriceItems.getText().isEmpty() || InputDescriptionItems.getText().isEmpty() ||
                comboboxCategoryItems.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please fill in all the field");
            alert.setTitle("Message");
            alert.showAndWait();
        }
    }

    public void goSimpleReport(ActionEvent actionEvent) {
        JasperPrint jp;
        Connection conn = MyConnection.getConnection();
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/Items.jasper", param, conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("All Data Report");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void goGroupReport(ActionEvent actionEvent) {
        JasperPrint jp;
        Connection conn = MyConnection.getConnection();
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/ItemsCategory.jasper", param, conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Group Data Report");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
