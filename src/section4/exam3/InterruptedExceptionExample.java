package section4.exam3;

public class InterruptedExceptionExample {

    public static void main(String[] args) throws  InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
                Thread.sleep(5000);
                System.out.println("스레드 잠자기 완료");
            } catch (InterruptedException e) { // InterruptedException 예외가 발생하면 인터럽트 상태가 초기화 된다 : false
                System.out.println("스레드가 인터럽트 되었습니다.");
                System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        });

        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        thread.join();
        System.out.println("인터럽트 상태 3: " + thread.isInterrupted());

        /*
        * 스레드가 wait, sleep, join 일 때 interrupt 만나면 InterruptedException 발생함
        * InterruptedException 호출되면 interrupted 상태 false 로 초기화 된다.
        *
        * 인터럽트 상태 1: false
        * 메인 스레드 2초간 Timed Wait Status
        * thread 5초간  Timed Wait Status
        * thread.interrupt() 호출 -> thread interrupted 상태 true 로 변경
        * thread 는 Timed Wait Status 상태 였는데 interrupted 를 만나서 InterruptedException 발생함
        * thread.join 호출 메인 스레드 대기
        * 스레드가 인터럽트 되었습니다.
        * 인터럽트 상태 2: false
        * 인터럽트 상태 3: true
         * */
    }
}
