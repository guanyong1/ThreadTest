package com.thread.test;

/**
 * @Author:guang yong
 * Description:线程的互斥与通信
 * @Date:Created in 12:26 2018/7/9
 * @Modified By:
 */
public class TraditionalThreadSyn {

    public static void main(String[] args) {
        new TraditionalThreadSyn().init();
    }

    private void init(){
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("debang");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("gailun");
                }
            }
        }).start();
    }

    static class Outputer{
        public void output(String name){
            synchronized (Outputer.class){
                int len = name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
        }
        }

        public static synchronized void output2(String name){
            int len = name.length();
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }

}
