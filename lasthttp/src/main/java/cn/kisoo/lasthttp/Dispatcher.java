package cn.kisoo.lasthttp;

import java.util.concurrent.ExecutorService;

/**
 * Created by kangqizhou on 2017/5/29.
 */

public final class Dispatcher {
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private Runnable idleCallback;

    private ExecutorService executorService;


    public Dispatcher(){}

    public Dispatcher(ExecutorService executorService){
        this.executorService = executorService;
    }


}
