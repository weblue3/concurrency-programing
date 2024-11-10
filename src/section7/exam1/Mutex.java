package section7.exam1;

public class Mutex {

    private boolean lock = false;

    public synchronized void acquired() {
        System.out.println("LOCK 진입 스레드 : " + Thread.currentThread().getName());
        while(lock){
            try {
                System.out.println("대기 상태 스레드 : " + Thread.currentThread().getName());
                wait();
                System.out.println("대기 상태 탈출 스레드 : " + Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.lock = true;
        System.out.println("LOCK 획득한 스레드 : " + Thread.currentThread().getName());
    }

    public synchronized void release() {
        System.out.println("LOCK 해제 스레드 : " + Thread.currentThread().getName());
        this.lock = false;
        this.notify();
    }
}
