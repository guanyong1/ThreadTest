package com.thread.test;

/**
 * @Author:guang yong
 * Description:传统创建线程的方式
 * @Date:Created in 11:03 2018/7/9
 * @Modified By:
 */
public class TraditionalThread {

    public static void main(String[] args){

        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //打印当前线程名字
                    System.out.println(Thread.currentThread().getName());
                }
            }
        };
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //打印当前线程名字
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        thread1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //打印当前线程名字
                    System.out.println("Runnable:"+Thread.currentThread().getName());
                }
            }
        }){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //打印当前线程名字
                    System.out.println("Thread:"+Thread.currentThread().getName());
                }
            }
        }.start();
    }
}
