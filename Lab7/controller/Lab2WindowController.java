package Lab7.controller;

import Lab2.Animals.Animal;
import Lab7.MainApp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lab2WindowController {
    private MainApp mainApp;
    private FileChooser fileChooser;
    private ObservableList<Animal> animals = FXCollections.observableArrayList();

    @FXML
    private TableView<Animal> tableView;

    @FXML
    private TableColumn<Animal, String> idColumn;

    @FXML
    private TableColumn<Animal, String> nameColumn;

    @FXML
    private TableColumn<Animal, String> foodTypeColumn;

    @FXML
    private TableColumn<Animal, Integer> foodAmountColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        foodTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFood().getType()));
        foodAmountColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getFood().getAmount()).asObject());

        deleteButton.setDisable(true);

        tableView.setItems(animals);
        tableView.getSelectionModel().selectedItemProperty().addListener(((observableValue, animal, newAnimal) ->
                deleteButton.setDisable(newAnimal == null)));

        fileChooser = new FileChooser();
    }

    @FXML
    private void handleLoad() {
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            ArrayList<Animal> loaded = new ArrayList<>();
            try {
                readFile(loaded, file);
                tableView.getItems().setAll(loaded);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Invalid fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Incorrect format!");

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleSave() {
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (file != null) {
            try {
                List<Animal> saving = tableView.getItems();
                writeFile(tableView.getItems(), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleAdd() {
        Animal animal = showNewAnimalDialog();
        if (animal != null) {
            tableView.getItems().add(animal);
        }
    }

    @FXML
    private void handleDelete() {
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
    }

    private  void readFile(List<Animal> list, File file) throws IOException, ClassNotFoundException {
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                try {
                    list.add((Animal) objectIn.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
    }

    @FXML
    private void handleBack() {
        mainApp.openMainMenu();
    }

    private void writeFile(List<Animal> list, File file) throws IOException {
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(file));
            for (Animal animal : list) {
                objectOut.writeObject(animal);
            }
    }

    private Animal showNewAnimalDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(mainApp.getClass().getResource("view/NewAnimalDialog.fxml"));

            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Animal");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            dialogStage.setScene(new Scene(loader.load()));

            NewAnimalDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            return controller.getAnimal();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
