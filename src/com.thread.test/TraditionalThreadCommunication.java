package com.thread.test;

/**
 * @Author:guang yong
 * Description:传统线程同步通信技术
 * @Date:Created in 15:47 2018/7/9
 * @Modified By:
 */
public class TraditionalThreadCommunication {

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
}
class Business{
    private boolean aBoolean = true;
    public synchronized void sub(int i){
        while (!aBoolean){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("线程1："+j+"线程2："+i);
        }
        aBoolean = false;
        this.notify();
    }

    public synchronized void main(int i){
        while (aBoolean){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println("main:线程1："+j+"线程2："+i);
        }

        aBoolean = true;
        this.notify();
    }
}
