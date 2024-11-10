package section7.exam3;

public class ConditionSynchronizationExample {

    private boolean isAvailable = false;

    public synchronized void produce() {
        while (isAvailable){
            try {
                System.out.println(Thread.currentThread().getName() + " 생산 대기");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + " 생산됨.");
        isAvailable = true;
        notify();
    }

    public synchronized void consume() {
        while (!isAvailable){
            try {
                System.out.println(Thread.currentThread().getName() + " 소비 대기");
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 소비됨.");
        isAvailable = false;
        notify();
    }

    public static void main(String[] args) {
        ConditionSynchronizationExample example = new ConditionSynchronizationExample();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.produce();
            }
        }, "producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.consume();
            }
        }, "consumer");

        producer.start();
        consumer.start();
    }
}
