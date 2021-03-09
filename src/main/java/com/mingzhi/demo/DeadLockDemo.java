package com.mingzhi.demo;

/**
 *死锁
 */
public class DeadLockDemo {

    private static final Object HAIR_A = new Object();
    private static final Object HAIR_B = new Object();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                synchronized(HAIR_A) {
                    System.out.println("synchronized A");
                    try {
                        Thread.sleep(50L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (HAIR_B) {
                        System.out.println("A成功的抓住B的头发");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                synchronized (HAIR_B) {
                    System.out.println("synchronized B");
                    synchronized (HAIR_A) {
                        System.out.println("B成功抓到A的头发");
                    }
                }
            }
        }).start();

    }
}
