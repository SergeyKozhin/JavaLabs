package Lab6.Banking;

public class BankSynchronized extends Bank {

    public BankSynchronized(int size) {
        for (int i = 0; i < size; ++i) {
            accounts.put(String.valueOf(i), new AccountSynchronized());
        }
    }

    public void transfer(String fromId, String toId, long amount) {
        Account fromAccount = accounts.get(fromId);
        Account toAccount = accounts.get(toId);

        Account firstLock, secondLock;
        if (fromId.compareTo(toId) < 0) {
            firstLock = fromAccount;
            secondLock = toAccount;
        } else {
            firstLock = toAccount;
            secondLock = fromAccount;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        }
    }
}
