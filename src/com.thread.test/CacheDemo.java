package com.thread.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author:guang yong
 * Description:
 * @Date:Created in 10:19 2018/7/17
 * @Modified By:
 */
public class CacheDemo {
    private Map<String,Object> cache = new HashMap<String,Object>();
    public static void main(String[] args) {

    }

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public Object getData(String key){
        readWriteLock.readLock().lock();
        Object value = null;
        try{
            //缓存中取数据
            value = cache.get(key);
            if(value == null){
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try{
                    if(value == null){
                        value = "aaaa";//缓存中取不到数据时，去数据库查询数据
                    }
                }finally {
                    readWriteLock.writeLock().unlock();
                    readWriteLock.readLock().lock();
                }
            }
        }finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }
}
