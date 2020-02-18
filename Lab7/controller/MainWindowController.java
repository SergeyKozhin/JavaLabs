package Lab7.controller;

import Lab7.MainApp;
import javafx.fxml.FXML;

public class MainWindowController {
    private MainApp mainApp;

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

    @FXML
    private void handleLab4() {
        mainApp.openLab4();
    }

    @FXML
    private void handleLab5() {
        mainApp.openLab5();
    }

    @FXML
    private void handleLab6() {
        mainApp.openLab6();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
