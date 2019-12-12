package Lab7.controller;

import Lab3.UndoStringBuilder;
import Lab7.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Lab3WindowController {
    private MainApp mainApp;
    private UndoStringBuilder undoStringBuilder = new UndoStringBuilder();

    @FXML
    private Label stringLabel;

    @FXML
    private TextField appendField;

    @FXML
    private TextField deleteFromField;

    @FXML
    private TextField insertWhatField;

    @FXML
    private TextField insertWhereField;

    @FXML
    private TextField replaceFromField;

    @FXML
    private TextField replaceToField;

    @FXML
    private TextField deleteToField;

    @FXML
    private TextField replaceWithField;

    @FXML
    private void handleAppend() {
        undoStringBuilder.append(appendField.getText());
        update();
    }

    @FXML
    private void handleDelete() {
        try {
            undoStringBuilder.delete(Integer.parseInt(deleteFromField.getText()),
                    Integer.parseInt(deleteToField.getText()));
            update();
        } catch (NumberFormatException e) {
            mainApp.showErrorWindow("Indexes are not integers!");
        } catch (StringIndexOutOfBoundsException e) {
            mainApp.showErrorWindow("Indexes out of bounds!");
        }
    }

    @FXML
    private void handleInsert() {
        try {
            undoStringBuilder.insert(Integer.parseInt(insertWhereField.getText()), insertWhatField.getText());
            update();
        } catch (NumberFormatException e) {
            mainApp.showErrorWindow("Indexes are not integers!");
        } catch (StringIndexOutOfBoundsException e) {
            mainApp.showErrorWindow("Indexes out of bounds!");
        }
    }

    @FXML
    private void handleReplace() {
        try {
            undoStringBuilder.replace(Integer.parseInt(replaceFromField.getText()),
                    Integer.parseInt(replaceToField.getText()), replaceWithField.getText());
            update();
        } catch (NumberFormatException e) {
            mainApp.showErrorWindow("Indexes are not integers!");
        } catch (StringIndexOutOfBoundsException e) {
            mainApp.showErrorWindow("Indexes out of bounds!");
        }
    }

    @FXML
    private void handleReverse() {
        undoStringBuilder.reverse();
        update();
    }

    @FXML
    private void handleUndo() {
        undoStringBuilder.undo();
        update();
    }

    @FXML
    private void handleBack() {
        mainApp.openMainMenu();
    }

    private void update() {
        stringLabel.setText(undoStringBuilder.toString());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
