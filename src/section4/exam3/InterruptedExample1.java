package section4.exam3;

public class InterruptedExample1 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {     //-- 2. interrupted 처음 상태는 false
                System.out.println("스레드가 작동 중입니다.");
            }
            System.out.println("스레드가 인터럽트 되었습니다."); //-- 4. 루프 상태 탈출

            /*
            * interrupted() 는 interrupted 멤버 변수의 값 읽어서 자신의 boolean 변수에 값 복사 후 true 인 경우
            * 멤버 변수의 interrupted 값을 false 로 설정 후 native clearInterruptEvent 호출
            *
            * public static boolean interrupted() {
            *        Thread t = currentThread();
            *        boolean interrupted = t.interrupted;
            *        // We may have been interrupted the moment after we read the field,
            *        // so only clear the field if we saw that it was set and will return
            *        // true; otherwise we could lose an interrupt.
            *        if (interrupted) {
            *            t.interrupted = false;
            *            clearInterruptEvent();
            *        }
            *        return interrupted;
            *    }
            * */

            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());   //-- 5. false 반환
        });

        thread.start();                         //-- 1. thread 시작

        try {
            Thread.sleep(1000);           //-- 2. 메인 스레드 Sleep  Time wait state
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();                     //-- 3. thread interrupted 상태 true 변경
    }
}
