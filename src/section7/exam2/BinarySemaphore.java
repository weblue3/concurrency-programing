package section7.exam2;

public class BinarySemaphore implements CommonSemaphore{

    private int signal = 1;

    @Override
    public synchronized void acquired() {
        while (signal == 0){
            try{
                System.out.println(Thread.currentThread().getName() + "대기");
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();     //-- 현재 스레드의 인터럽트 상태를 설정
            }
        }

        this.signal = 0;
        System.out.println(Thread.currentThread().getName() + "LOCK 획득");
    }

    @Override
    public synchronized void release() {
        this.signal = 1;
        System.out.println(Thread.currentThread().getName() + "Notify 알림");
        notify();
        System.out.println(Thread.currentThread().getName() + "LOCK 반환");
    }
}
