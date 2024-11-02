package section5.exam5;

public class ThreadLocalExample {

    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Hello World");
    //-- private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException{

        //-- 첫 번째 스레드 : ThreadLocal 값을 설정하고 출력
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            threadLocal.set("스레드 1의 값");
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }, "Thread-1");
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            threadLocal.set("스레드 2의 값");
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }, "Thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
    }
}
