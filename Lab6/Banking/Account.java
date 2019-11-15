package Lab6.Banking;

public abstract class Account {
    long amount;

    public Account(long amount) {
        this.amount = amount;
    }

    public Account() {
        this(100000);
    }

    public abstract void deposit(long amount);

    public abstract void withdraw(long amount);

    public long getAmount() {
        return amount;
    }
}
