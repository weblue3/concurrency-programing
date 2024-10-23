package section3.exam3;

public class NewStateThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("스레드 실행중");
        });
        //-- 스레드 상태 New : 스레드를 생성한 상태
        System.out.println("스레드 상태 : " + thread.getState());
    }
}
