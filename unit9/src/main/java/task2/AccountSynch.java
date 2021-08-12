package task2;

public class AccountSynch implements Runnable {

    private final static Object MONITOR = new Object();
    private final Account accountFrom;
    private final Account accountTo;
    private final int money;

    public AccountSynch(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
            int from = accountFrom.hashCode();
            int to = accountTo.hashCode();

            if (from > to) {
                synchronized (accountFrom) {
                    synchronized (accountTo) {
                        transfer();
                    }
                }
            } else if (to > from) {
                synchronized (accountTo) {
                    synchronized (accountFrom) {
                        transfer();
                    }
                }
            } else {
                synchronized (MONITOR) {
                    synchronized (accountFrom) {
                        synchronized (accountTo) {
                            transfer();
                        }
                    }
                }
            }
        }
    }

    private void transfer() {
        System.out.printf("BEFORE: accountFrom: - %d, accountTo: - %d, ",
                          accountFrom.getCacheBalance(),
                          accountTo.getCacheBalance());
        if (accountFrom.takeOffMoney(money)) {
            accountTo.addMoney(money);
            System.out.printf("AFTER: accountFrom: - %d, accountTo: - %d, money - %s%n",
                              accountFrom.getCacheBalance(),
                              accountTo.getCacheBalance(), money);
        } else {
            System.out.printf("money - %d%n", money);
        }
    }
}