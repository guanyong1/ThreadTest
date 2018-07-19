package com.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:guang yong
 * Description:三个condition线程通信技术
 * @Date:Created in 15:47 2018/7/9
 * @Modified By:
 */
public class ThreeConditionCommunication {

    public static void main(String[] args) {
        final Business business =new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                            for (int i = 1; i <= 50 ; i++) {
                               business.sub2(i);
                            }
                    }
                }
        ).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50 ; i++) {
                    business.sub3(i);
                }
            }
        }).start();

        for (int i = 1; i <= 50 ; i++) {
           business.main(i);
        }
    }

    static class Business{
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        private int aBoolean = 1;
        public void sub2(int i){
            lock.lock();
            try{
                while (aBoolean != 2){
                    try {
                        condition2.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("线程2："+j+"循环："+i);
                }
                aBoolean = 3;
                condition3.signal();
            }finally {
                lock.unlock();
            }

        }

        public void sub3(int i){
            lock.lock();
            try{
                while (aBoolean != 3){
                    try {
                        condition3.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 20; j++) {
                    System.out.println("线程3："+j+" 循环："+i);
                }
                aBoolean = 1;
                condition1.signal();
            }finally {
                lock.unlock();
            }

        }

        public void main(int i){
            lock.lock();
            try{
                while (aBoolean!=1){
                    try {
                        condition1.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("线程1："+j+" 循环："+i);
                }

                aBoolean = 2;
                condition2.signal();
            }finally {
                lock.unlock();
            }

        }
    }
}

