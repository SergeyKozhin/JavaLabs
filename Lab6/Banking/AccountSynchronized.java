package Lab6.Banking;

public class AccountSynchronized extends Account {

    public AccountSynchronized(long amount) {
        super(amount);
    }

    public AccountSynchronized() {
        super();
    }

    public synchronized void deposit(long amount) {
        this.amount += amount;
    }

    public synchronized void withdraw(long amount) {
        this.amount -= amount;
    }

    public synchronized long getAmount() {
        return amount;
    }
}
