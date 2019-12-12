package Lab7.controller;

import Lab4.FileExplorer;
import Lab7.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.io.PrintStream;

public class Lab4WindowController {
    private FileExplorer fileExplorer;
    private MainApp mainApp;

    @FXML
    private TextArea console;

    @FXML
    private TextField inputField;

    @FXML
    private void initialize() {
        PrintStream out = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                Platform.runLater(() -> {
                    console.appendText(String.valueOf((char) b));
                });
            }
        }, true);

        fileExplorer = new FileExplorer(System.in, out);
        fileExplorer.execute("\n");

        Platform.runLater(() -> inputField.requestFocus());
    }

    @FXML
    private void handleSubmit() {
        console.appendText(inputField.getText() + "\n");
        fileExplorer.execute(inputField.getText());
        inputField.setText("");
    }

    @FXML
    private void handleBack() {
        mainApp.openMainMenu();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
