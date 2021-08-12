package task2.trylock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Account account1 = new Account(100000, new ReentrantLock());
        Account account2 = new Account(500000, new ReentrantLock());

        AccountTryLock thread1 = new AccountTryLock(account1, account2, 10000);
        AccountTryLock thread2 = new AccountTryLock(account2, account1, 5000);

        CompletableFuture.allOf(
            CompletableFuture.runAsync(thread1),
            CompletableFuture.runAsync(thread2)
        ).join();

        System.out.println("account1 - " + account1.getCacheBalance());
        System.out.println("account2 - " + account2.getCacheBalance());
    }
}
