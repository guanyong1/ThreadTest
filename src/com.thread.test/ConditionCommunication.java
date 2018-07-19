package com.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:guang yong
 * Description:condition线程同步通信技术
 * @Date:Created in 15:47 2018/7/9
 * @Modified By:
 */
public class ConditionCommunication {

    public static void main(String[] args) {
        final Business business =new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                            for (int i = 1; i <= 50 ; i++) {
                               business.sub(i);
                            }
                    }
                }
        ).start();

        for (int i = 1; i <= 50 ; i++) {
           business.main(i);
        }
    }

    static class Business{
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        private boolean aBoolean = true;
        public void sub(int i){
            lock.lock();
            try{
                while (!aBoolean){
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("线程1："+j+"线程2："+i);
                }
                aBoolean = false;
                condition.signal();
            }finally {
                lock.unlock();
            }

        }

        public void main(int i){
            lock.lock();
            try{
                while (aBoolean){
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("main:线程1："+j+"线程2："+i);
                }

                aBoolean = true;
                condition.signal();
            }finally {
                lock.unlock();
            }

        }
    }
}

