package Lab6.Banking;

import java.io.PrintStream;
import java.util.concurrent.locks.Lock;

public class BankConcurrent extends Bank {

    public BankConcurrent(int size) {
        for (int i = 0; i < size; ++i) {
            accounts.put(String.valueOf(i), new AccountConcurrent());
        }
    }

    public void transfer(String fromId, String toId, long amount, PrintStream out) {
        AccountConcurrent fromAccount = (AccountConcurrent) accounts.get(fromId);
        AccountConcurrent toAccount = (AccountConcurrent) accounts.get(toId);

        if (fromAccount.getAmount() < amount) {
            out.println("Transaction denied! Account " + fromId + " has " + fromAccount.getAmount() +
                    ", but " + amount + " required!");
            return;
        }

        acquireLocks(fromAccount.getLock(), toAccount.getLock());
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        } finally {
            fromAccount.getLock().unlock();
            toAccount.getLock().unlock();
        }
    }

    private void acquireLocks(Lock lock1, Lock lock2) {
        while (true) {
            boolean locked1 = lock1.tryLock();
            boolean locked2 = lock2.tryLock();

            if (locked1 && locked2) {
                break;
            }

            if (locked1) {
                lock1.unlock();
            }

            if (locked2) {
                lock2.unlock();
            }
        }
    }
}
