package gui;

import app.ConnectionConfig;
import entities.ConnectionEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class TestGeneratorController {
    private String connectionPath = "E:\\Goldbug-Testing-Tool\\run\\configs\\global_connections_config\\testConfigApp.xml";
    private ConnectionConfig connectionConfig;
    private List<String> sourceConnection = new ArrayList<>();
    private List<String> targetConnection = new ArrayList<>();

    @FXML
    ComboBox<ConnectionEntity> targetComboBox;
    @FXML
    ComboBox<ConnectionEntity> sourceComboBox;

    @FXML
    private void initialize() {
        initConnectionList(connectionPath);
        sourceComboBox.getItems().clear();
        sourceComboBox.setItems(FXCollections.observableArrayList(connectionConfig.getSourceConnectionEntitiesList()));
        targetComboBox.setItems(FXCollections.observableArrayList(connectionConfig.getTargetConnectionEntitiesList()));
    }

    private void initConnectionList(String path) {
        connectionConfig = new ConnectionConfig(path);
    }
}
