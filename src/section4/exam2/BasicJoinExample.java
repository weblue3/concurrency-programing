package section4.exam2;

public class BasicJoinExample {
    public static void main(String[] args) {

        /**
        * Main Thread 가 thread 실행 -> main 이 join 호출 -> main Time Waited State
         * thread Runnable -> sleep -> thread Time Waited State -> sleep end Runnable -> Terminate
         * -> main notify -> main runnable -> main Terminated
        * */


        Thread thread = new Thread(() -> {
            try {
                System.out.println("스레드가 3초 동안 작동합니다.");
                Thread.sleep(3000);
                System.out.println("스레드 작동 완료.");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        thread.start();

        System.out.println("메인 스레드가 다른 스레드의 완료를 기다립니다.");

        try {
            thread.join();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }


        System.out.println("메인 스레드가 계속 진행합니다.");
    }
}
