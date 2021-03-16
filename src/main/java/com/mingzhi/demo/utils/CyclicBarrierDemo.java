package com.mingzhi.demo.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环屏障 允许一组线程相互等待直到所有线程都到达一个公共的屏障点。 可重用
 */
public class CyclicBarrierDemo {

    //private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new CBAction());

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(finalI *1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"已上车");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("人满发车");
                }
            }).start();
        }


    }

    static class CBAction implements Runnable{
        @Override
        public void run() {
            System.out.println("人满发车2");
        }
    }
}
