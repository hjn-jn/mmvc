package com.util.thread;

import java.util.concurrent.ExecutorService;

public class ThreadUtil {

    public static boolean shownDown(ExecutorService es){
        es.shutdownNow();
        return es.isShutdown();
    }

}
