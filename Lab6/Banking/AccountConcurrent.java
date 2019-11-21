package Lab6.Banking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountConcurrent extends Account {
    private Lock lock  = new ReentrantLock();

    public AccountConcurrent(long amount) {
        super(amount);
    }

    public AccountConcurrent() {
        super();
    }

    public void deposit(long amount) {
        lock.lock();
        try {
            this.amount += amount;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void withdraw(long amount) {
        lock.lock();
        try {
            this.amount -= amount;
        } finally {
            lock.unlock();
        }
    }

    public long getAmount() {
        lock.lock();
        try {
            return amount;
        } finally {
            lock.unlock();
        }
    }

    public Lock getLock() {
        return lock;
    }
}
