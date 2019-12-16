package Lab7.controller;

import Lab6.Banking.Account;
import Lab6.Banking.Bank;
import Lab6.Banking.BankConcurrent;
import Lab6.Banking.BankSynchronized;
import Lab7.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;


public class Lab6WindowController {
    private MainApp mainApp;
    private File file = null;
    private PrintStream out;

    @FXML
    private TextArea console;

    @FXML
    private Button synchronizedButton;

    @FXML
    private Button concurrentButton;

    @FXML
    private void initialize() {
        out = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                Platform.runLater(() -> console.appendText(Character.toString((char) b)));
            }
        });

        console.textProperty().addListener((observableValue, s, t1) -> console.setScrollTop(0));

        synchronizedButton.setDisable(true);
        concurrentButton.setDisable(true);
    }

    @FXML
    private void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            synchronizedButton.setDisable(false);
            concurrentButton.setDisable(false);
        } else {
            synchronizedButton.setDisable(true);
            concurrentButton.setDisable(true);
        }
    }

    @FXML
    private void handleSynchronized() {
        launchBank(new BankSynchronized(10));
    }

    @FXML
    private void handleConcurrent() {
        launchBank(new BankConcurrent(10));
    }

    @FXML
    private void handleBack() {
        mainApp.openMainMenu();
    }

    private void launchBank(Bank bank) {
        console.setText("");
        try {
            bank.executeTransfersFromFile(file.getName(), out);
            printBankStatistics(bank);
        } catch (FileNotFoundException e) {
            mainApp.showErrorWindow("Cannot access file: " + e.getMessage());
        } catch (InterruptedException e) {
            mainApp.showErrorWindow("Execution is interrupted");
        }
    }

    private void printBankStatistics(Bank bank) {
        out.println("Money on each account:");
        bank.getAccounts().forEach((key, value) -> out.println(key + ": " + value.getAmount()));
        out.println("Total money in bank: " +
                bank.getAccounts().values().stream().mapToLong(Account::getAmount).sum());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
