package section5.exam1;

public class DefaultExceptionHandlerExample {


    public static void main(String[] args) {
        //-- 모든 스레드의 예외에[ 대한 기본 핸들러 설정
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("예외가 발생한 스레드 이름 : " + t.getName());
            System.out.println("예외 메시지 : " + e.getMessage());
        });


        // 예외를 발생시키는 여러 스레드
        Thread thread1 = new Thread(() -> {
            throw new RuntimeException("스레드 1 예외!");
        }, "스레드-1");

        Thread thread2 = new Thread(() -> {
            throw new RuntimeException("스레드 2 예외!");
        }, "스레드-2");

        thread1.start();
        thread2.start();

    }

}
