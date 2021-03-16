package com.mingzhi.demo.utils;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * 是通过一个计数器来实现的，计数器的初始化值为线程的数量。
 * 每当一个线程完成了自己的任务后，计数器的值就相应得减1。
 * 当计数器到达0时，表示所有的线程都已完成任务，然后在闭锁上等待的线程就可以恢复执行任务。
 */
public class CountDownLatchDemo {
    //demo1
    //private static CountDownLatch countDownLatch = new CountDownLatch(5);

    //demo2
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatchSon = new CountDownLatch(5);

    public static void main(String[] args) {
        //1.某一线程在开始运行前等待n个线程执行完毕
        //demo1();
        //2.实现多个线程开始执行任务的最大并行性
        demo2();
    }

    private static void demo1(){
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(finalI * 2000L);
                        System.out.println(Thread.currentThread().getName() + "运行完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            }).start();;
        }

        System.out.println("等待子线程运行");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程运行结束");
    }

    private static void demo2(){
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "准备运行");
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "运行结束");
                    countDownLatchSon.countDown();
                }
            }).start();;
        }

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println("子线程开始运行");
        try {
            countDownLatchSon.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程二阶段开始");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程二阶段结束");
    }
}
