package section7.exam1;

public class MutexExample {

    public static void main(String[] args) throws InterruptedException {

        Mutex mutex = new Mutex();
        SharedData sharedData = new SharedData(mutex);

        Thread t1 = new Thread(sharedData::sum, "T1 스레드");
        Thread t2 = new Thread(sharedData::sum, "T2 스레드");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("최종 합계:" + sharedData.getSharedValue());

    }
}
