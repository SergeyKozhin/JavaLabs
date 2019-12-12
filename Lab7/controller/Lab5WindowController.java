package Lab7.controller;

import Lab7.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class Lab5WindowController {
    private MainApp mainApp;
    private ObservableList<Pair<String, String>> properties = FXCollections.observableArrayList();

    @FXML
    private TableView<Pair<String, String>> tableView;

    @FXML
    private TableColumn<Pair<String, String>, String> nameColumn;

    @FXML
    private TableColumn<Pair<String, String>, String> valueColumn;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        valueColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        tableView.setItems(properties);
    }

    @FXML
    private void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            try (InputStream input = new FileInputStream(file)){
                Properties properties = new Properties();
                properties.load(input);

                tableView.getItems().setAll(List.of());
                properties.forEach((key, value) ->
                        tableView.getItems().add(new Pair<String, String>(key.toString(), value.toString())));
            } catch (FileNotFoundException e) {
                mainApp.showErrorWindow("No such file: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                mainApp.showErrorWindow("Illegal symbols in input" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleBack() {
        mainApp.openMainMenu();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
