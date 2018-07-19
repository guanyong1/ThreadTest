package com.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 11:50 2018/7/18
 * @Modified By:
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        //缓存线程池
        ExecutorService service = Executors.newCachedThreadPool();
        //获取三个信号量
        final Semaphore sp = new Semaphore(3);
        for (int i = 0; i < 50; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
                        sp.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //sp.availablePermits()返回此信号量中当前可用的许可数。
                    System.out.println("线程"+Thread.currentThread().getName()+"进入，当前已有" +(3 - sp.availablePermits()) +"个并发");
                    try {
                        Thread.sleep((long)Math.random()*10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程"+Thread.currentThread().getName()+"即将离开");
                    //释放一个许可，将其返回给信号量
                    sp.release();
                    System.out.println("线程"+Thread.currentThread().getName()+"已离开,当前有"+(3 - sp.availablePermits()) +"个并发");
                }
            };
            // 执行给定runnable
            service.execute(runnable);
        }

    }
}
