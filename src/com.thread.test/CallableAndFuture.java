package com.thread.test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 10:41 2018/7/16
 * @Modified By:
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future=
        threadPool.submit(new Callable<String>() {
            public String call() throws InterruptedException {
                Thread.sleep(2000);
                return "hello";
            }
        });
        System.out.println("等待结果");
        try {
            System.out.println("拿到结果："+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        ExecutorService threadPool1 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool1);
        for (int i = 1; i <= 10 ; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
