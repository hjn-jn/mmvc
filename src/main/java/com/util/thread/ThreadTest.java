package com.util.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadTest extends Thread {
    private String name;
    private String result;
    private int sleepTime;

    public ThreadTest(String name){
        this.name = name;
    }

    public String getResult(){
        return result;
    }

    public int getSleepTime(){
        return sleepTime;
    }

    @Override
    public void run() {
        Random r = new Random();
        for (int i = 0;i<10 ;i++ ) {
            int t = r.nextInt(2);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sleepTime = sleepTime +t;
            System.out.println("sleep:"+sleepTime);

        }
        result = name+":sleep "+sleepTime+" seconds";

    }

}
