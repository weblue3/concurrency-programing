package section4.exam3;

public class IsInterruptedExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("스레드가 작동 중입니다.");
            }
            System.out.println("스레드가 인터럽트 되었습니다.");
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        });

        thread.setName("Check Thread");
        thread.start();        //-- Check Thread 시작 os schedule 에 의해서 run 호출

        try {
            Thread.sleep(1000); //-- main thread  time waited
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //-- volatile : 레지스터에 로드된 값을 사용하지 않고 매번 메모리를 참조한다.
        //-- 컴파일러가 최적화 수행하지 않고 매번 메모리를 참조한다.
        /**
         * 요약하자면 각 스레드는 메인 메모리 로부터 값을 복사해 CPU 캐시 에 저장하여 작업한다.
         * CPU 가 2개 이상이라면 멀티 스레드 환경에서 각 스레드는 서로 다른 CPU 에서 동작하고 있으며 이는 각 스레드가
         * 같은 변수에 대해 읽기, 쓰기 동작을 수행할 시 각자의 CPU 캐시 에 메인 메모리 의 값과 다른 값을 갖고 있을 수 있게 된다.
         *   하지만 자바에서 어떠한 변수에 volatile 키워드를 붙이면 해당 변수는 모든 읽기와 쓰기 작업이 CPU 캐시가 아닌
         *   메인 메모리 에서 이루어지게 되고 이로써 해당 변수 값에 대해 가시성 을 보장할 수 있다.
         *   사실 이 가시성 이란 용어는 읽기, 쓰기 작업에 대해 '동시성 문제 를 해결해준다' 는 의미가 아니다. 메인 메모리에서 작업이 이루어진다는 이유로 해당 키워드만으로 동기화 처리가 이루어진다고 잘못 받아들이면 다음과 같이
         *  https://velog.io/@naneun/Java-Volatile-%ED%82%A4%EC%9B%8C%EB%93%9C%EB%9E%80
         * */
        thread.interrupt();         //-- Check Thread 의 volatile boolean interrupted 값 true 로 변경
    }
}
