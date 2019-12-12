package Lab7.view;

import Lab7.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Lab3WindowController {
    private MainApp mainApp;

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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
