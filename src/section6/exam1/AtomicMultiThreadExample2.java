package section6.exam1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicMultiThreadExample2 {

    private static AtomicInteger sum = new AtomicInteger(0);
    private static final Object lock = new Object();

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {

            for (int i = 1; i <= 500; i++) {
                sum.addAndGet(i);
            }
        }, "스레드-1");

        Thread thread2 = new Thread(() -> {

            for (int i = 501; i <= 1000; i++) {
                sum.addAndGet(i);
            }
        }, "스레드-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("합계: " + sum.get());
        System.out.println("멀티 스레드 처리 시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
