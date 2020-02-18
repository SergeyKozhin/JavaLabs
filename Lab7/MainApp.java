package Lab7;

import Lab7.controller.MainWindowController;
import Lab7.controller.Lab1WindowController;
import Lab7.controller.Lab2WindowController;
import Lab7.controller.Lab3WindowController;
import Lab7.controller.Lab4WindowController;
import Lab7.controller.Lab5WindowController;
import Lab7.controller.Lab6WindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openMainMenu();
        primaryStage.show();
    }

    public void openMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/MainWindow.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Main menu");

            MainWindowController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLab1() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Lab1Window.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Lab 1");

            Lab1WindowController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLab2() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Lab2Window.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Lab 2");

            Lab2WindowController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLab3() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Lab3Window.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Lab 3");

            Lab3WindowController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLab4() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Lab4Window.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Lab 4");

            Lab4WindowController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLab5() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Lab5Window.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Lab 5");

            Lab5WindowController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLab6() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/Lab6Window.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Lab 6");

            Lab6WindowController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showErrorWindow(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(primaryStage);
        alert.setTitle("Invalid fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(message);

        alert.showAndWait();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
