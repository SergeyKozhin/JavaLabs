package Lab6.Banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class Bank {
    Map<String, Account> accounts = new HashMap<>();

    public abstract void transfer(String fromId, String toId, long amount);

    public void executeTransfersFromFile(String fileName) throws FileNotFoundException, InterruptedException {
        Scanner in = new Scanner(new FileInputStream(fileName));
        ExecutorService executor = Executors.newCachedThreadPool();

        while (in.hasNextLine()) {
            String[] transfer = in.nextLine().split(",");
            executor.submit(() -> transfer(transfer[0], transfer[1], Integer.parseInt(transfer[2])));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
