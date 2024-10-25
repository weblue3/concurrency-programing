package section4.exam2;

public class InterruptedJoinExample {

    public static void main(String[] args) {

        Thread longRunningThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("긴 작업 스레드가 계속 실행 중...");
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
                System.out.println("긴 작업 스레드가 인터럽트 되었습니다.");
            }
        });

        longRunningThread.start();

        Thread interruptingThread = new Thread(() -> {
            try {
                System.out.println("인터럽트 스레드가 2초 후에 긴 작업 스레드를 인터럽트 합니다.");
                Thread.sleep(2000);
                System.out.println("인터럽트 스레드가 긴 작업 스레드 interrupt 호출");
                longRunningThread.interrupt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        interruptingThread.start();

        System.out.println("메인 스레드가 다른 스레드의 완료를 기다립니다.");

        try {
            longRunningThread.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("메인 스레드가 계속 진행합니다.");
    }
}
