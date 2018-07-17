package com.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 10:05 2018/7/16
 * @Modified By:
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //创建线程池

        //固定线程池(正规线程)
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //缓存线程池
        // ExecutorService threadPool = Executors.newCachedThreadPool();
        //单线程线程池(实现线程死掉后重新启动)
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i < 10; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "is loop of " +j + " for task "+ task);
                    }
                }
            });
        }
        System.out.println("all of 10 tasks have committed");
        //关闭线程池
        threadPool.shutdown();

        //定时器启动线程池
        Executors.newScheduledThreadPool(3).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing");
            }
        },10, TimeUnit.SECONDS);
    }
}
