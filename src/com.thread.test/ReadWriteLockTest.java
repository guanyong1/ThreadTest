package com.thread.test;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 11:41 2018/7/16
 * @Modified By:
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        final Queue3 queue3 = new Queue3();
        for (int i = 0; i  < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        queue3.get();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        queue3.put(new Random().nextInt(10000));
                    }
                }
            }).start();

        }
    }
}

class Queue3{
    //共享数据，只能有一个线程写该数据，但可以多个线程同时读该数据
    private Object data = null;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void get(){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" be ready to reade data");
            Thread.sleep((long)(Math.random()*1000));
            System.out.println(Thread.currentThread().getName()+" have reade data:"+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }

    public void put(Object data){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" be ready to write data");
            Thread.sleep((long)(Math.random()*1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName()+" have write data:"+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }
}
