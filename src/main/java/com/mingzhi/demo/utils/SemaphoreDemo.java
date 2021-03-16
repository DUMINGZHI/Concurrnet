package com.mingzhi.demo.utils;

import java.util.concurrent.Semaphore;

/**
 * 可以维护当前访问自身的线程个数 例子 接口限流
 */
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(8);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                       semaphore.release();
                    }

                }
            }).start();
        }
    }
}
