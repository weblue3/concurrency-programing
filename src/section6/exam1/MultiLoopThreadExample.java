package section6.exam1;

public class MultiLoopThreadExample {

    private static int sum = 0;
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        ThreadLocal<NumberCounter> threadLocal = new ThreadLocal<>();
        for (int i = 1; i <= 10; i++) {

            NumberCounter numberCounter = new NumberCounter(i);

            Thread thread = new Thread(() -> {
                threadLocal.set(numberCounter);
                int threadLocalSum = threadLocal.get().getSum();
                System.out.println("[" + Thread.currentThread().getName() + "]" + ", sum: " + sum);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sum += threadLocalSum;
                System.out.println("[" + Thread.currentThread().getName() + "]" + ", threadLocalSum: " + threadLocalSum + ", 결과: " + sum);

            }, "연산 스레드-" + i);

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("==========================");
        System.out.println("sum: " + sum);
        System.out.println("소요시간: " + (System.currentTimeMillis() - start) + "ms");
    }

    static class NumberCounter{
        int startNum;
        int sum;

        public NumberCounter(int startNum) {
            this.startNum = startNum;
        }

        int getSum(){

            int start = (this.startNum - 1) * 100 + 1;
            int end = this.startNum * 100;

            for (int i = start; i <= end; i++) {
                this.sum += i;
            }

            return this.sum;
        }
    }
}
