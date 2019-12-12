package Lab7.controller;

import Lab7.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class MainWindowController {
    private MainApp mainApp;

    public MainWindowController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleLab1() {
        mainApp.openLab1();
    }

    @FXML
    private void handleLab2() {
        mainApp.openLab2();
    }

    @FXML
    private void handleLab3() {
        mainApp.openLab3();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
