package com.mingzhi.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义一个可重入的锁
 */
public class MyReentryLockDemo implements Lock {

    private boolean holdLock = false;
    private Thread holdThread = null;
    private int reentryCount = 0;

    @Override
    public void lock() {
        if(holdLock && Thread.currentThread()!=holdThread){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        holdLock = true;
        holdThread = Thread.currentThread();
        reentryCount++;
    }

    @Override
    public synchronized void unlock() {
        if(holdThread == Thread.currentThread()){
            reentryCount--;
            if(reentryCount==0){
                holdLock = false;
                notify();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {

    }
}
