package section8.exam1;

public class SynchronizedExamples {

    private int instanceCount = 0;
    private static int staticCount = 0;

    public synchronized void instanceMethod() {
        instanceCount++;
        System.out.println("인스턴스 메서드 동기화: " + instanceCount);
    }

    public static synchronized void staticMethod() {
        staticCount++;
        System.out.println("정적 메서드 동기화: " + staticCount);
    }

    public void instanceBlock() {
        synchronized (this){
            instanceCount++;
            System.out.println("인스턴스 Block 동기화: " + instanceCount);
        }
    }

    public static void staticBlock() {
        synchronized (SynchronizedExamples.class){
            staticCount++;
            System.out.println("정적 Block 동기화: " + staticCount);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        SynchronizedExamples examples = new SynchronizedExamples();

        new Thread(examples::instanceMethod).start();
        new Thread(examples::instanceBlock).start();
        new Thread(SynchronizedExamples::staticMethod).start();
        new Thread(SynchronizedExamples::staticBlock).start();
    }

}
