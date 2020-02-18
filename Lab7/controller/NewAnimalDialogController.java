package Lab7.controller;

import Lab2.Animals.Animal;
import Lab2.Animals.Carnivore;
import Lab2.Animals.Herbivore;
import Lab2.Animals.Omnivore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.UUID;

public class NewAnimalDialogController {
    private Stage dialogStage;
    private Animal animal = null;

    private ObservableList<String> types = FXCollections.observableArrayList(
            "Carnivore",
            "Herbivore",
            "Omnivore"
    );

    @FXML
    private TextField idField;

    @FXML
    private ComboBox<String> typeField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField foodTypeField;

    @FXML
    private TextField foodAmountField;

    @FXML
    private void initialize() {
        typeField.setItems(types);
        typeField.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleOK() {
        if (isInputValid()) {
            String type = typeField.getSelectionModel().getSelectedItem();
            switch (type) {
                case "Carnivore":
                    animal = new Carnivore(nameField.getText(),
                            new Animal.Food(foodTypeField.getText(), Integer.parseInt(foodAmountField.getText())));
                    break;
                case "Herbivore":
                    animal = new Herbivore(nameField.getText(),
                            new Animal.Food(foodTypeField.getText(), Integer.parseInt(foodAmountField.getText())));
                    break;
                case "Omnivore":
                    animal = new Omnivore(nameField.getText(),
                            new Animal.Food(foodTypeField.getText(), Integer.parseInt(foodAmountField.getText())));
                    break;
            }

            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder("");

        if (idField.getText() == null || idField.getText().length() == 0) {
            idField.setText(UUID.randomUUID().toString());
        }
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage.append("No valid name!\n");
        }

        if (foodTypeField.getText() == null || foodTypeField.getText().length() == 0) {
            errorMessage.append("No valid food type!\n");
        }

        if (foodAmountField.getText() == null || foodAmountField.getText().length() == 0) {
            errorMessage.append("No valid food amount!\n");
        } else {
            try {
                Integer.parseInt(foodAmountField.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("No valid food amount. Must be integer!\n");
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();

            return false;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Animal getAnimal() {
        return animal;
    }
}
