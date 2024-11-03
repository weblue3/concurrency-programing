package section6.exam1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicMultiThreadExample {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        AtomicInteger sum = new AtomicInteger(0);
        AtomicInteger nextVal1 = new AtomicInteger(1);
        AtomicInteger nextVal2 = new AtomicInteger(501);

        Thread thread1 = new Thread(() -> {
            while (nextVal1.get() <= 500){
                System.out.println("현재 스레드 : " + Thread.currentThread().getName() + " nextVal : " + nextVal1.get());
                sum.updateAndGet(n -> n + nextVal1.get());
                nextVal1.getAndIncrement();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "스레드-1");

        Thread thread2 = new Thread(() -> {
            while (nextVal2.get() <= 1000){
                System.out.println("현재 스레드 : " + Thread.currentThread().getName() + " nextVal : " + nextVal2.get());
                sum.updateAndGet(n -> n + nextVal2.get());
                nextVal2.getAndIncrement();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
