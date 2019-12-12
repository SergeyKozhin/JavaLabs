package Lab7.controller;

import Lab1.TaskC.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookEditDialogController {
    private Stage dialogStage;

    @FXML
    private TextField idField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField yearField;

    private boolean OKClicked;
    private Book book;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleOKClicked() {
        if (isInputValid()) {
            book.setId(Integer.parseInt(idField.getText()));
            book.setAuthor(authorField.getText());
            book.setName(nameField.getText());
            book.setYear(Integer.parseInt(yearField.getText()));

            OKClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancelClicked() {
        dialogStage.close();
    }

    public void setBook(Book book) {
        this.book = book;
        if (book == null) {
            idField.setText("");
            authorField.setText("");
            nameField.setText("");
            yearField.setText("");
        } else {
            idField.setText(Integer.toString(book.getId()));
            authorField.setText(book.getAuthor());
            nameField.setText(book.getName());
            yearField.setText(Integer.toString(book.getYear()));
        }
    }

    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder("");

        if (idField.getText() == null || idField.getText().length() == 0) {
            errorMessage.append("No valid id!\n");
        } else {
            try {
                Integer.parseInt(idField.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("No valid id. Must be integer!\n");
            }
        }
        if (authorField.getText() == null || authorField.getText().length() == 0) {
            errorMessage.append("No valid author!\n");
        }
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage.append("No valid book name!\n");
        }

        if (yearField.getText() == null || yearField.getText().length() == 0) {
            errorMessage.append("No valid year!\n");
        } else {
            try {
                Integer.parseInt(yearField.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("No valid year. Must be integer!\n");
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

    public boolean isOKClicked() {
        return OKClicked;
    }
}
