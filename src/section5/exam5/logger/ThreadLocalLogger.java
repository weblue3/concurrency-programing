package section5.exam5.logger;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalLogger {
    private static final ThreadLocal<List<String>> THREAD_LOG = ThreadLocal.withInitial(ArrayList::new);

    //--private static final List<String> THREAD_LOG = new ArrayList<>();

    //-- 로그 추가
    public static void addLog(String log) {
        THREAD_LOG.get().add(log);
        //THREAD_LOG.add(log);
    }

    //-- 로그 출력
    public static void printLog() {
        List<String> logs = THREAD_LOG.get();
        System.out.println("[" + Thread.currentThread().getName() + "] 로그: " + String.join(" -> ", logs));
        //System.out.println("[" + Thread.currentThread().getName() + "] 로그: " + String.join(" -> ", THREAD_LOG));
    }

    //-- 로그 초기화
    public static void clearLog(){
        THREAD_LOG.remove();
        //THREAD_LOG.clear();
    }

    static class ServiceA {
        public void process() {
            addLog("ServiceA 로직 수행");
        }
    }

    static class ServiceB {
        public void process() {
            addLog("ServiceB 로직 수행");
        }
    }

    static class ServiceC {
        public void process() {
            addLog("ServiceC 로직 수행");
        }
    }
}
