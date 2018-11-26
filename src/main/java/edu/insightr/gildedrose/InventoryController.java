package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {


    private ObservableList<Item> itemData = FXCollections.observableArrayList();
    @FXML
    private TableView<Item> itemTable;
    private Inventory inventory = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventory = new Inventory();
        itemData.setAll(inventory.getItems());
        itemTable.getItems().setAll(itemData);
    }

    public void onUpdate() {
        inventory.updateQuality();
        itemData.setAll(inventory.getItems());
        itemTable.getItems().setAll(itemData);
    }

    public void onLoad() {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File(getClass().getResource("/json").getFile()));
            fc.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
            File selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                inventory.addItems(inventory.ListItemsFromJSON(selectedFile.getAbsolutePath()));
                itemData.setAll(inventory.getItems());
                itemTable.getItems().setAll(itemData);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
