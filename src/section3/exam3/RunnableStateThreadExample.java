package section3.exam3;

public class RunnableStateThreadExample {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
           while(true){
               for (int i=0; i<1000000000; i++){
                   if(i%1000000000 == 0){
                       System.out.println("스레드 상태: " + Thread.currentThread().getState()); // RUNNABLE
                   }
               }
           }
        });

        thread.start();
    }
}
