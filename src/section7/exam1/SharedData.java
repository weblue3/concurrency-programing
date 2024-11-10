package section7.exam1;

public class SharedData {

    private int sharedValue = 0;
    private final Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum(){
        try {
            mutex.acquired();   //-- LOCK 획득
            for (int i = 0; i < 10_000_000; i++) {
                sharedValue++;
            }
        }finally {
            mutex.release();    //-- LOCK 해제
        }
    }

    public int getSharedValue() {
        return sharedValue;
    }
}
