package com.mingzhi.demo.utils;

import java.util.concurrent.Exchanger;

/**
 * 数据交换 成双出现
 */
public class ExchangerDemo {
    static Exchanger exchanger = new Exchanger();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object obj1 = exchanger.exchange("dmz");
                    System.out.println("dmz->>>" + obj1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object obj2 = exchanger.exchange("ok");
                    System.out.println("ok->>>" + obj2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
