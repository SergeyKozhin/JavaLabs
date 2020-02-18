package Lab6;

import Lab6.Banking.Account;
import Lab6.Banking.Bank;
import Lab6.Banking.BankConcurrent;
import Lab6.Banking.BankSynchronized;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;


public class Main {
    private static final int AMOUNT_OF_ACCOUNTS = 10;
    private  static final String FILE_NAME = "transfers.txt";

    public static void main(String[] args) {
        try {
            generateData(FILE_NAME, AMOUNT_OF_ACCOUNTS);

            System.out.println("With synchronized.");
            Bank bankSynchronized = new BankSynchronized(AMOUNT_OF_ACCOUNTS);
            bankSynchronized.executeTransfersFromFile(FILE_NAME, System.out);
            printBankStatistics(bankSynchronized);
            System.out.println();

            System.out.println("With concurrent");
            Bank bankConcurrent = new BankConcurrent(AMOUNT_OF_ACCOUNTS);
            bankConcurrent.executeTransfersFromFile(FILE_NAME, System.out);
            printBankStatistics(bankConcurrent);

        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void generateData(String fileName, int accountsCount) throws FileNotFoundException {
        Random random = new Random();
        PrintWriter out = new PrintWriter(new FileOutputStream(fileName));

        for (int i = 0; i < 1000; ++i) {
            out.println("" + random.nextInt(accountsCount) + ',' + random.nextInt(10) + ',' + random.nextInt(10000));
        }

        out.close();
    }

    public static void printBankStatistics(Bank bank) {
        System.out.println("Money on each account:");
        bank.getAccounts().forEach((key, value) -> System.out.println(key + ": " + value.getAmount()));
        System.out.println("Total money in bank: " +
                bank.getAccounts().values().stream().mapToLong(Account::getAmount).sum());
    }

}
