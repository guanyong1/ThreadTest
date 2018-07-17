package com.thread.test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author:guang yong
 * Description:定时器的应用
 * @Date:Created in 11:51 2018/7/9
 * @Modified By:
 */
public class TraditionalTimerTest {

    public static void main(String[] args){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("boom");
            }
        },10000,3000);
    }
}
