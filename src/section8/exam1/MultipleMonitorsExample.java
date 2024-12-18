package section8.exam1;

class BankAccount {
    private double balance;
    private final Object lock = new Object();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount){
        synchronized (lock){
            balance += amount;
        }
    }

    public boolean withdraw(double amount){
        synchronized (lock){
            if(balance < amount){
                return false;
            }

            balance -= amount;
            return true;
        }
    }

    //-- 내계좌에서 상대방 계좌로 돈을 송금
    public boolean transfer(BankAccount to, double amount){
        synchronized (this.lock){
            if(this.withdraw(amount)){      //-- 출금이 정상적으로 이루어지면 true;
                synchronized (to.lock){
                    to.deposit(amount);
                    return true;
                }
            }
            return false;
        }
    }

    public double getBalance() {
        synchronized (lock){
            return balance;
        }
    }
}

public class MultipleMonitorsExample {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(1000);

        // accountA 에서 accountB 로 송금하는 스레드
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountA.transfer(accountB, 10);
                if(result){
                    System.out.println("accountA에서 accountB로 10 송금 성공");
                }else{
                    System.out.println("accountA에서 accountB로 10 송금 실패");
                }
            }
        });

        // accountB 에서 accountA 로 송금하는 스레드
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountB.transfer(accountA, 10);
                if(result){
                    System.out.println("accountB에서 accountA로 10 송금 성공");
                }else{
                    System.out.println("accountB에서 accountA로 10 송금 실패");
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("accountA의 최종 잔액: " + accountA.getBalance());
        System.out.println("accountB의 최종 잔액: " + accountB.getBalance());
    }
}
