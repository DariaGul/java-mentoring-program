package task2.trylock;

import java.util.concurrent.locks.ReentrantLock;


public class Account {

    private int cacheBalance;
    private ReentrantLock lock;

    public Account(int cacheBalance, ReentrantLock lock) {
        this.cacheBalance = cacheBalance;
        this.lock = lock;
    }

    public void addMoney(int money) {
        this.cacheBalance += money;
    }

    public boolean takeOffMoney(int money) {
        if (this.cacheBalance < money) {
            return false;
        }

        this.cacheBalance -= money;
        return true;
    }

    public int getCacheBalance() {
        return cacheBalance;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}