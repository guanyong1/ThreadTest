package com.thread.test;

import java.util.Random;

/**
 * @Author:guang yong
 * Description:使用TheadLocal实现线程范围内的数据共享
 * @Date:Created in 15:54 2018/7/10
 * @Modified By:
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()+
                            " has put data :"+data);
                    MyTreadScopeData.getInstance().setName("name:"+data);
                    MyTreadScopeData.getInstance().setAge(data);
                    new ThreadLocalTest.A().get();
                    new ThreadLocalTest.B().get();
                }
            }).start();
        }
    }

    static class A{

        public void get(){
            MyTreadScopeData myData = MyTreadScopeData.getInstance();
            System.out.println("A from "+Thread.currentThread().getName()+
                    " has put name :"+myData.getName()+","+myData.getAge());
        }
    }

    static class B{

        public void get(){
            MyTreadScopeData myData = MyTreadScopeData.getInstance();
            System.out.println("B from "+Thread.currentThread().getName()+
                    " has put name :"+myData.getName()+","+myData.getAge());
        }
    }
}

class MyTreadScopeData{
    //单列模式

    //饿汉模式
    /*
    //私有无参构造
    private MyTreadScopeData(){}
    public  static MyTreadScopeData getInstance(){
        return instance;
    }
    private static MyTreadScopeData instance = new MyTreadScopeData();*/

    //懒汉模式
    private static MyTreadScopeData instance = null;
    //私有无参构造
    private MyTreadScopeData(){}
    public  static MyTreadScopeData getInstance(){
        MyTreadScopeData instance = map.get();
        if(instance == null){
            instance = new MyTreadScopeData();
            map.set(instance);
        }
        return instance;
    }

    private static ThreadLocal<MyTreadScopeData> map = new ThreadLocal<MyTreadScopeData>();
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
