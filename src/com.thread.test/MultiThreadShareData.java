package com.thread.test;

/**
 * @Author:guang yong
 * Description:多个线程之间的数据共享
 * @Date:Created in 11:54 2018/7/11
 * @Modified By:
 */
public class MultiThreadShareData {

    public static void main(String[] args) {
        ShareData data = new ShareData();
        new Thread(new MyRunnable1(data)).start();
        new Thread(new MyRunnable2(data)).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                data.decremenmt();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                data.increment();
            }
        }).start();
    }
}

class MyRunnable1 implements Runnable{
    private ShareData data;
    public MyRunnable1(ShareData data){
        this.data = data;
    }
    @Override
    public void run() {
        data.decremenmt();
    }
}

class MyRunnable2 implements Runnable{
    private ShareData data;
    public MyRunnable2(ShareData data){
        this.data = data;
    }
    @Override
    public void run() {
        data.increment();
    }
}

class ShareData{
    private int j = 0;
    public synchronized void increment(){
        j++;
    }

    public synchronized void decremenmt(){
        j--;
    }
}
 