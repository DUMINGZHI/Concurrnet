package com.mingzhi.demo.communication;

/**
 * 测试1.wait会释放*当前对象*的锁
 * 测试2.notify和notifyAll都是唤醒其他正在等待*同一个对象锁*的线程
 */
public class TestWaitNotifyDemo {

    private synchronized void sayHello(){
        System.out.println(Thread.currentThread().getName() + "进入等待。");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("延时3s");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束等待");
    }

    private synchronized void sayNihao(){
        System.out.println("延时3s");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
        System.out.println("notifyAll done");
    }


    public static void main(String[] args) {
        TestWaitNotifyDemo testWaitNotifyDemo = new TestWaitNotifyDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testWaitNotifyDemo.sayHello();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testWaitNotifyDemo.sayHello();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //new Test().sayNihao();
                testWaitNotifyDemo.sayNihao();
            }
        }).start();
    }
}
