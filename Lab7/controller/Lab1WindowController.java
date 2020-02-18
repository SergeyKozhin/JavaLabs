package Lab7.controller;

import Lab7.MainApp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import Lab1.TaskC.Book;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Lab1WindowController {
    private MainApp mainApp;
    private ObservableList<Book>  library = FXCollections.observableArrayList();

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> nameColumn;

    @FXML
    private TableColumn<Book, Integer> yearColumn;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        yearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject());

        updateButton.setDisable(true);
        deleteButton.setDisable(true);

        tableView.setItems(library);
        tableView.getSelectionModel().selectedItemProperty().addListener(((observableValue, book, newValue) -> {
            if (newValue != null) {
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
            } else {
                updateButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        }));
    }

    @FXML
    private void handleNewBook() {
        Book book = new Book(0, "Author", "Name", 0);
        if (showEditBookDialog(book)) {
            tableView.getItems().add(book);
        }
    }

    @FXML
    private void handleUpdateBook() {
        Book book = tableView.getSelectionModel().getSelectedItem();
        showEditBookDialog(book);
        tableView.refresh();
    }

    @FXML
    private void handleDeleteBook() {
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void handleBack() {
        mainApp.openMainMenu();
    }

    private boolean showEditBookDialog(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(mainApp.getClass().getResource("view/BookEditDialog.fxml"));

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            dialogStage.setScene(new Scene(loader.load()));

            BookEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBook(book);

            dialogStage.showAndWait();

            return controller.isOKClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
