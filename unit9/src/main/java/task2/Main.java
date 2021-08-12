package task2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Account account1 = new Account(100000);
        Account account2 = new Account(500000);

//        ReentrantLock locker = new ReentrantLock();
//        AccountThread thread1 = new AccountThread(account1, account2, 10000, locker);
//        AccountThread thread2 = new AccountThread(account2, account1, 5000, locker);

        //AccountThreadDeadLock thread1 = new AccountThreadDeadLock(account1, account2, 10000);
        //AccountThreadDeadLock thread2 = new AccountThreadDeadLock(account2, account1, 5000);

        AccountSynch thread1 = new AccountSynch(account1, account2, 10000);
        AccountSynch thread2 = new AccountSynch(account2, account1, 5000);

        CompletableFuture.allOf(
            CompletableFuture.runAsync(thread1),
            CompletableFuture.runAsync(thread2)
        ).join();

        System.out.println("account1 - " + account1.getCacheBalance());
        System.out.println("account2 - " + account2.getCacheBalance());
    }
}
