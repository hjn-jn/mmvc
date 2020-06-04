package com.util.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RunnableTest implements Runnable {

    private String name;
    private String result;

    public RunnableTest(String name){
        this.name = name;
    }

    public String getResult(){
        return result;
    }

    @Override
    public void run() {
        Random r = new Random();
        int n = r.nextInt(5);
        System.out.println(n);
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
