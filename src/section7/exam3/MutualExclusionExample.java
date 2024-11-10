package section7.exam3;

public class MutualExclusionExample {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
        System.out.println("스레드: " + Thread.currentThread().getName() + ", 카운터 값: " + counter);
    }

    public static void main(String[] args) {

        MutualExclusionExample example = new MutualExclusionExample();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                example.increment();
            }
        });

        t1.start();
        t2.start();

    }
}
